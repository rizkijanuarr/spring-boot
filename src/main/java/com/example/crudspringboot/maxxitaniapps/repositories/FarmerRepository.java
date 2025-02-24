package com.example.crudspringboot.maxxitaniapps.repositories;

import com.example.crudspringboot.maxxitaniapps.repositories.entities.FarmerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmerRepository extends JpaRepository<FarmerEntity, String> {
    List<FarmerEntity> findAllByOrderByCreatedDateDesc();
    Slice<FarmerEntity> findAllByActiveTrueOrderByCreatedDateDesc(Pageable pageable);
    Slice<FarmerEntity> findAllByActiveFalseOrderByCreatedDateDesc(Pageable pageable);
}
