package com.example.crudspringboot.core.services.v1;

import com.example.crudspringboot.core.configs.security.JwtUtil;
import com.example.crudspringboot.core.repositories.RoleRepository;
import com.example.crudspringboot.core.repositories.UserRepository;
import com.example.crudspringboot.core.repositories.entities.RoleEntity;
import com.example.crudspringboot.core.repositories.entities.UserEntity;
import com.example.crudspringboot.core.request.RegisterRequestV1;
import com.example.crudspringboot.core.request.v1.LoginRequestV1;
import com.example.crudspringboot.core.response.v1.AuthResponseV1;
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

    public AuthResponseV1 register(RegisterRequestV1 req) {
        findRoleByName(req);
        UserEntity users = setUserInDatabase(req);
        return mapAuthToResponses(users);
    }

    public AuthResponseV1 login(LoginRequestV1 req) {
        getAuthenticationManager(req);
        UserEntity users = findUserById(req);
        return mapAuthToResponses(users);
    }



    private Optional<RoleEntity> findRoleByName(RegisterRequestV1 req) {
        return Optional.ofNullable(roleRepository.findByName(req.getRole())
                .orElseThrow(() -> new RuntimeException("Role Not Found")));
    }

    private UserEntity setUserInDatabase(RegisterRequestV1 req) {
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

    private UserEntity findUserById(LoginRequestV1 req) {
        return userRepository.login(req.getUser_email())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }

    private void getAuthenticationManager(LoginRequestV1 req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUser_email(), req.getUser_password())
        );
    }

    private AuthResponseV1 mapAuthToResponses(UserEntity entity) {
        String token = jwtUtils.generateToken(entity);
        return AuthResponseV1.builder()
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

}
