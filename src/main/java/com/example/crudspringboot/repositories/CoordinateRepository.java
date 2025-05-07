package com.example.crudspringboot.repositories;

import com.example.crudspringboot.repositories.entities.CoordinateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends JpaRepository<CoordinateEntity, String> {

}
