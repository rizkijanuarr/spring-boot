package com.example.crudspringboot.maxxitaniapps.repositories;

import com.example.crudspringboot.maxxitaniapps.repositories.entities.CoordinateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends JpaRepository<CoordinateEntity, String> {

}
