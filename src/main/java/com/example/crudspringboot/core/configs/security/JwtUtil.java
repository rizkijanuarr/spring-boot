package com.example.crudspringboot.core.configs.security;

import com.example.crudspringboot.core.configs.constant.ConstantSecurity;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private static final String SECRET_KEY = ConstantSecurity.SECRET;
    private static final long EXPIRATION_TIME = ConstantSecurity.EXPIRATION_TIME;
    private static final String ISSUER = ConstantSecurity.NAME_APP;

    // Generate token baru
    public String generateToken(String userEmail, String userId) {
        return JWT.create()
                .withSubject(userEmail)  // Set email sebagai subject
                .withClaim("user_id", userId) // Tambah custom claim with user_id
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    // Validasi token
    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.error("JWT Verification Failed: {}", e.getMessage());
            return false;
        }
    }

    // Ambil email dari token
    public String getUserEmailFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }
}


