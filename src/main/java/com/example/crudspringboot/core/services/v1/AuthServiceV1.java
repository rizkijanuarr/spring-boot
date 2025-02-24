package com.example.crudspringboot.core.services.v1;

import com.example.crudspringboot.core.configs.security.JwtUtil;
import com.example.crudspringboot.core.repositories.RoleRepository;
import com.example.crudspringboot.core.repositories.UserRepository;
import com.example.crudspringboot.core.repositories.entities.RoleEntity;
import com.example.crudspringboot.core.repositories.entities.UserEntity;
import com.example.crudspringboot.core.request.RegisterRequestV1;
import com.example.crudspringboot.core.request.v1.LoginRequestV1;
import com.example.crudspringboot.core.response.RegisterResponseV1;
import com.example.crudspringboot.core.response.v1.LoginResponseV1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceV1 {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public RegisterResponseV1 register(RegisterRequestV1 request) {
        Optional<RoleEntity> roleEntity = roleRepository.findByName(request.getRole());
        if (roleEntity.isEmpty()) {
            throw new RuntimeException("Role not found");
        }

        UserEntity account = new UserEntity();
        account.setUser_name(request.getUser_name());
        account.setUser_email(request.getUser_email());
        account.setUser_password(passwordEncoder.encode(request.getUser_password()));
        account.setUser_phone(request.getUser_phone());
        account.setRole(roleEntity.get());
        account.setCreatedBy(createdBy());
        account.setCreatedDate(createdDate());

        userRepository.save(account);

        String token = jwtUtils.generateToken(account);

        return new RegisterResponseV1(
                account.getId(),
                account.getUser_name(),
                account.getUser_email(),
                account.getUser_phone(),
                account.getRole().getName(),
                token
        );
    }

    // Handle login request
    public LoginResponseV1 login(LoginRequestV1 request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUser_email(), request.getUser_password())
        );

        UserEntity account = userRepository.login(request.getUser_email())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        String token = jwtUtils.generateToken(account);

        return new LoginResponseV1(
                account.getId(),
                account.getUser_name(),
                account.getUser_email(),
                account.getUser_phone(),
                account.getRole().getName(),
                token
        );
    }

    public String createdBy() {
        return "SYSTEM";
    }

    public Date createdDate() {
        return new Date();
    }

}
