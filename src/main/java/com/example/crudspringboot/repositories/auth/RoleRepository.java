package com.example.crudspringboot.repositories.auth;

import com.example.crudspringboot.repositories.entities.auth.RoleEntity;
import com.example.crudspringboot.utils.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
