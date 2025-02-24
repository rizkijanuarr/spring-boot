package com.example.crudspringboot.maxxitaniapps.repositories;

import com.example.crudspringboot.maxxitaniapps.repositories.entities.AreaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, String> {
    List<AreaEntity> findAllByOrderByCreatedDateDesc();
    Slice<AreaEntity> findAllByActiveTrueOrderByCreatedDateDesc(Pageable pageable);
    Slice<AreaEntity> findAllByActiveFalseOrderByCreatedDateDesc(Pageable pageable);
}
