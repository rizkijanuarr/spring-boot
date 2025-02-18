package com.example.crudspringboot.repositories;

import com.example.crudspringboot.repositories.entities.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<AreaEntity, String> {
    List<AreaEntity> findAllByOrderByCreatedDateDesc();
}
