package com.example.crudspringboot.services.auth;

import com.example.crudspringboot.configs.security.JwtUtil;
import com.example.crudspringboot.repositories.RoleRepository;
import com.example.crudspringboot.repositories.UserRepository;
import com.example.crudspringboot.repositories.entities.RoleEntity;
import com.example.crudspringboot.repositories.entities.UserEntity;
import com.example.crudspringboot.request.auth.RegisterRequest;
import com.example.crudspringboot.request.auth.LoginRequest;
import com.example.crudspringboot.response.auth.LoginResponse;
import com.example.crudspringboot.response.auth.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest req) {
        findRoleByName(req);
        UserEntity users = setUserInDatabase(req);
        return mapRegisterToResponses(users);
    }

    private RegisterResponse mapRegisterToResponses(UserEntity entity) {
        return RegisterResponse.builder()
                .id(entity.getId())
                .user_name(entity.getUser_name())
                .user_email(entity.getUser_email())
                .phone(entity.getUser_phone())
                .role(entity.getRole().getName())
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    public LoginResponse login(LoginRequest req) {
        getAuthenticationManager(req);
        UserEntity users = findUserById(req);
        return mapLoginToResponses(users);
    }

    private Optional<RoleEntity> findRoleByName(RegisterRequest req) {
        return Optional.ofNullable(roleRepository.findByName(req.getRole())
                .orElseThrow(() -> new RuntimeException("Role Not Found")));
    }

    private UserEntity setUserInDatabase(RegisterRequest req) {
        findRoleByName(req);

        UserEntity account = new UserEntity();
        account.setUser_name(req.getUser_name());
        account.setUser_email(req.getUser_email());
        account.setUser_password(passwordEncoder.encode(req.getUser_password()));
        account.setUser_phone(req.getUser_phone());
        account.setRole(findRoleByName(req).get());
        account.setCreatedBy(createdBy());
        account.setCreatedDate(createdDate());

        return userRepository.save(account);
    }

    private UserEntity findUserById(LoginRequest req) {
        return userRepository.login(req.getUser_email())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }

    private void getAuthenticationManager(LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUser_email(), req.getUser_password())
        );
    }

    private LoginResponse mapLoginToResponses(UserEntity entity) {
        String token = jwtUtils.generateToken(entity);
        
        return LoginResponse.builder()
                .id(entity.getId())
                .user_name(entity.getUser_name())
                .user_email(entity.getUser_email())
                .phone(entity.getUser_phone())
                .role(entity.getRole().getName())
                .token(token)
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    public String createdBy() {
        return "SYSTEM";
    }

    public Date createdDate() {
        return new Date();
    }

    public Map<String, Boolean> logout(String token) {
        // Remove "Bearer " prefix if present
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }


        if (!jwtUtils.isTokenValid(token)) {
            throw new RuntimeException("Invalid token");
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return response;
    }

}
