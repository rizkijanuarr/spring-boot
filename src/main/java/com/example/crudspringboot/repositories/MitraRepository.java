package com.example.crudspringboot.repositories;

import com.example.crudspringboot.repositories.entities.MitraEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MitraRepository extends JpaRepository<MitraEntity, String> {

    // contoh ketika menggunakan Pageable
    Slice<MitraEntity> findAllByOrderByCreatedDateDesc(Pageable pageable);
    // Case berbagai macam GET ALL
    List<MitraEntity> findAllByOrderByCreatedDateDesc(); // Terbaru ke terlama
    List<MitraEntity> findAllByOrderByCreatedDateAsc(); // Terlama ke terbaru
    List<MitraEntity> findAllByOrderByIdAsc(); // ID terkecil ke terbesar // A - Z
    List<MitraEntity> findAllByOrderByIdDesc(); // ID terbesar ke terkecil // Z - A

    Slice<MitraEntity> findAllByActiveTrueOrderByCreatedDateDesc(Pageable pageable);
    Slice<MitraEntity> findAllByActiveFalseOrderByCreatedDateDesc(Pageable pageable);

}
