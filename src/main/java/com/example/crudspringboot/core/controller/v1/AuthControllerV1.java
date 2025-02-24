package com.example.crudspringboot.core.controller.v1;

import com.example.crudspringboot.core.request.RegisterRequestV1;
import com.example.crudspringboot.core.request.v1.LoginRequestV1;
import com.example.crudspringboot.core.response.RegisterResponseV1;
import com.example.crudspringboot.core.response.v1.LoginResponseV1;
import com.example.crudspringboot.core.services.v1.AuthServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthControllerV1 {

    private final AuthServiceV1 authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseV1> register(@RequestBody RegisterRequestV1 request) {
        RegisterResponseV1 response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseV1> login(@RequestBody LoginRequestV1 request) {
        LoginResponseV1 response = authService.login(request);
        return ResponseEntity.ok(response);
    }

}
