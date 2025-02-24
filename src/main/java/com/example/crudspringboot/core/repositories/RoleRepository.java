package com.example.crudspringboot.core.repositories;

import com.example.crudspringboot.core.repositories.entities.RoleEntity;
import com.example.crudspringboot.core.utils.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
