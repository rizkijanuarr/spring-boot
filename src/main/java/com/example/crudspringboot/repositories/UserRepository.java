package com.example.crudspringboot.repositories;

import com.example.crudspringboot.repositories.entities.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findAllByOrderByCreatedDateDesc();
    Slice<UserEntity> findAllByActiveTrueOrderByCreatedDateDesc(Pageable pageable);
    Slice<UserEntity> findAllByActiveFalseOrderByCreatedDateDesc(Pageable pageable);

    @Query(
            value = "select * from user where user_email = ? and deleted_by IS NULL",
            nativeQuery = true
    )
    Optional<UserEntity> login(String email);
}
