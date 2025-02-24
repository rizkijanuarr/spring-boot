package com.example.crudspringboot.core.services.v1;

import com.example.crudspringboot.core.repositories.UserRepository;
import com.example.crudspringboot.core.repositories.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceV1 implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.login(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return new User(user.getUser_email(), user.getUser_password(), authorities);
    }
}
