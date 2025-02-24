package com.example.crudspringboot.core.configs.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.crudspringboot.core.configs.constant.ConstantHeader;
import com.example.crudspringboot.core.configs.constant.ConstantSecurity;
import com.example.crudspringboot.core.repositories.UserRepository;
import com.example.crudspringboot.core.repositories.entities.UserEntity;
import com.example.crudspringboot.core.services.v1.UserServiceV1;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.example.crudspringboot.core.configs.constant.ConstantSecurity.BEARER_TOKEN_PREFIX;
import static com.example.crudspringboot.core.configs.constant.ConstantSecurity.SECRET;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtils;
    private final UserRepository userRepository;
    private final String HEADER_AUTH = ConstantHeader.HEADER_AUTH;
    private final String BEARER_TOKEN = BEARER_TOKEN_PREFIX;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header = request.getHeader(ConstantHeader.HEADER_AUTH);
        System.out.println("Header Authorization: " + header); // Cek header masuk atau tidak

        if (header == null || !header.startsWith(ConstantSecurity.BEARER_TOKEN_PREFIX)) {
            System.out.println("Token tidak ditemukan atau salah format!");
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(ConstantSecurity.BEARER_TOKEN_PREFIX, "");
        System.out.println("Extracted Token: " + token); // Cek apakah token berhasil diekstrak
        String email = jwtUtils.validateToken(token);
        System.out.println("Decoded Email: " + email); // Cek apakah email berhasil diekstrak dari token

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserEntity account = userRepository.login(email).orElse(null);

            if (account != null) {
                System.out.println("User ditemukan: " + account.getUser_email()); // Pastikan user ditemukan

                String roleName = "ROLE_" + account.getRole().getName().name();
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(roleName));

                // Debugging
                System.out.println("User Authorities: " + authorities);

                UserDetails userDetails = new User(account.getUser_email(), account.getUser_password(), authorities);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("Authentication Set: " + SecurityContextHolder.getContext().getAuthentication());
            }
        }

        chain.doFilter(request, response);
    }
}
