package com.example.crudspringboot.core.configs.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.crudspringboot.core.configs.constant.ConstantHeader;
import com.example.crudspringboot.core.services.v1.UserServiceV1;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.crudspringboot.core.configs.constant.ConstantSecurity.BEARER_TOKEN_PREFIX;
import static com.example.crudspringboot.core.configs.constant.ConstantSecurity.SECRET;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserServiceV1 userDetailsService;  // Service untuk load user

    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        String token = req.getHeader(ConstantHeader.HEADER_AUTH); // Ambil token dari header

        // Skip jika tidak ada token
        if (token == null || !token.startsWith(BEARER_TOKEN_PREFIX)) {
            chain.doFilter(req, response);
            return;
        }

        String token1 = token.substring(7); // Hapus prefix "Bearer "

        try {
            System.out.println("Token yang akan diverifikasi: " + token1);

            // Verify token
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token1);

            System.out.println("Token berhasil diverifikasi!");

            // Ambil data dari token
            String email = jwt.getSubject();
            String userId = jwt.getClaim("user_id").asString();

            // Set ke request attributes
            req.setAttribute(ConstantHeader.HEADER_X_ID, userId);
            req.setAttribute(ConstantHeader.HEADER_X_WHO, email);

            System.out.println("Email dari token: " + email);
            System.out.println("User_id dari token: " + userId);

            // Set authentication
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lanjut ke filter berikutnya
            chain.doFilter(req, response);

        } catch (Exception e) {
            System.out.println("Error verifikasi token: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
    }
}
