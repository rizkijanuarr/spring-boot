package com.example.crudspringboot.controller.auth;

import com.example.crudspringboot.annotations.swagger.PostEndpoint;
import com.example.crudspringboot.annotations.swagger.SwaggerTypeGroup;
import com.example.crudspringboot.controller.advices.BaseController;
import com.example.crudspringboot.request.auth.RegisterRequest;
import com.example.crudspringboot.request.auth.LoginRequest;
import com.example.crudspringboot.response.auth.LoginResponse;
import com.example.crudspringboot.response.auth.RegisterResponse;
import com.example.crudspringboot.services.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@BaseController("auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostEndpoint(
            value = "/register",
            tagName = "User",
            description = "Register",
            group = SwaggerTypeGroup.DEFAULT
    )
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostEndpoint(
            value = "/login",
            tagName = "User",
            description = "Login",
            group = SwaggerTypeGroup.DEFAULT
    )
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @PostEndpoint(
            value = "/refresh",
            tagName = "User",
            description = "Refresh Token",
            group = SwaggerTypeGroup.APPS_WEB
    )
    public ResponseEntity<LoginResponse> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        LoginResponse response = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostEndpoint(
            value = "/logout",
            tagName = "User",
            description = "Logout",
            group = SwaggerTypeGroup.APPS_WEB
    )
    public ResponseEntity<Map<String, Boolean>> logout(@RequestHeader("Authorization") String token) {
        Map<String, Boolean> response = authService.logout(token);
        return ResponseEntity.ok(response);
    }
}
