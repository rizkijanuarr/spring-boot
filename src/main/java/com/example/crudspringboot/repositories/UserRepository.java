package com.example.crudspringboot.repositories;

import com.example.crudspringboot.repositories.entities.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findAllByOrderByCreatedDateDesc();
    Slice<UserEntity> findAllByActiveTrueOrderByCreatedDateDesc(Pageable pageable);
}
