package com.example.crudspringboot.core.services.v1;

import com.example.crudspringboot.core.configs.security.JwtUtil;
import com.example.crudspringboot.core.repositories.UserRepository;
import com.example.crudspringboot.core.repositories.entities.UserEntity;
import com.example.crudspringboot.core.request.v1.LoginRequestV1;
import com.example.crudspringboot.core.response.v1.LoginResponseV1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceV1 {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // Handle login request
    public LoginResponseV1 login(LoginRequestV1 request) {
        // Cari user berdasarkan email
        UserEntity user = userRepository.login(request.getUser_email())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        // Cek password dengan BCrypt
        if (!passwordEncoder.matches(request.getUser_password(), user.getUser_password())) {
            throw new RuntimeException("Password salah");
        }

        // Generate token JWT
        String token = jwtUtil.generateToken(user.getUser_email(), user.getId());

        return new LoginResponseV1(user.getUser_name(), user.getUser_email(), token);
    }

}
