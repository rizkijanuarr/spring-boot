package com.example.crudspringboot.configs.security;

import com.example.crudspringboot.configs.constant.ConstantHeader;
import com.example.crudspringboot.configs.constant.ConstantSecurity;
import com.example.crudspringboot.repositories.entities.UserEntity;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private final String secret = ConstantSecurity.SECRET;
    private final long expirationTime = ConstantSecurity.EXPIRATION_TIME;
    private final String X_WHO = ConstantHeader.HEADER_X_WHO;
    private final String X_ROLE = ConstantHeader.HEADER_X_ROLE;

    // Generate JWT Token
    public String generateToken(UserEntity user) {
        return JWT.create()
                .withSubject(user.getUser_email())
                .withClaim("id", user.getId())
                .withClaim("user_name", user.getUser_name())
                .withClaim("role", user.getRole().getName().name())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + ConstantSecurity.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(ConstantSecurity.SECRET));
    }

    // Validate JWT Token
    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(ConstantSecurity.SECRET))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC256(ConstantSecurity.SECRET))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getClaim(String token, String claim) {
        return JWT.require(Algorithm.HMAC256(ConstantSecurity.SECRET))
                .build()
                .verify(token)
                .getClaim(claim)
                .asString();
    }

}


