package com.example.crudspringboot.repositories;

import com.example.crudspringboot.repositories.entities.FarmerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<FarmerEntity, String> {
}
