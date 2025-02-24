package com.example.crudspringboot;

import com.example.crudspringboot.core.repositories.RoleRepository;
import com.example.crudspringboot.core.repositories.entities.RoleEntity;
import com.example.crudspringboot.core.utils.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if(roleRepository.count() == 0) {
            for(RoleEnum roleEnum : RoleEnum.values()) {
                RoleEntity role = new RoleEntity();
                role.setName(roleEnum);
                role.setCreatedBy(createdBy());
                role.setCreatedDate(createdDate());

                roleRepository.save(role);
            }
        }
    }

    public String createdBy() {
        return "SYSTEM";
    }

    public Date createdDate() {
        return new Date();
    }
}
