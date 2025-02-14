package com.example.crudspringboot.services.v1.impl;

import com.example.crudspringboot.repositories.MitraRepository;
import com.example.crudspringboot.repositories.entities.MitraEntity;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.services.v1.MitraServiceV1;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MitraServiceImplV1 implements MitraServiceV1 {
    private final MitraRepository mitraRepository;

    @Override
    public MitraResponseV1 create(MitraRequestV1 request) {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (request.getAddress() == null || request.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Alamat tidak boleh kosong");
        }
        if (request.getType() == null) {
            throw new IllegalArgumentException("Tipe tidak boleh null");
        }

        MitraEntity mitra = new MitraEntity();
        mitra.setName(request.getName());
        mitra.setAddress(request.getAddress());
        mitra.setType(request.getType());

        MitraEntity saved = mitraRepository.save(mitra);

        return MitraResponseV1.builder()
                .id(saved.getId())
                .name(saved.getName())
                .address(saved.getAddress())
                .type(saved.getType())
                .createdDate(mitra.getCreatedDate())
                .build();
    }

    @Override
    public MitraResponseV1 getById(String id) {
        MitraEntity mitra = mitraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mitra not found with id: " + id));

        return MitraResponseV1.builder()
                .id(mitra.getId())
                .name(mitra.getName())
                .address(mitra.getAddress())
                .type(mitra.getType())
                .createdDate(mitra.getCreatedDate())
                .build();
    }

    @Override
    public List<MitraResponseV1> getAll() {
        List<MitraEntity> mitraList = mitraRepository.findAllByOrderByCreatedDateDesc();

        return mitraList.stream()
                .map(mitra -> MitraResponseV1.builder()
                        .id(mitra.getId())
                        .name(mitra.getName())
                        .address(mitra.getAddress())
                        .type(mitra.getType())
                        .createdDate(mitra.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public MitraResponseV1 update(String id, MitraRequestV1 request) {
        MitraEntity mitra = mitraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mitra not found with id: " + id));

        mitra.setName(request.getName());
        mitra.setAddress(request.getAddress());
        mitra.setType(request.getType());

        MitraEntity updated = mitraRepository.save(mitra);

        return MitraResponseV1.builder()
                .id(updated.getId())
                .name(updated.getName())
                .address(updated.getAddress())
                .type(updated.getType())
                .createdDate(mitra.getCreatedDate())
                .build();
    }

    @Override
    public void delete(String id) {
        MitraEntity mitra = mitraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mitra not found with id: " + id));
        mitraRepository.delete(mitra);
    }
}