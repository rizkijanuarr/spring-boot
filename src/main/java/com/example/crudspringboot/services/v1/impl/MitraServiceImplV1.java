package com.example.crudspringboot.services.v1.impl;

import com.example.crudspringboot.repositories.MitraRepository;
import com.example.crudspringboot.repositories.entities.MitraEntity;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.services.v1.MitraServiceV1;
import com.example.crudspringboot.utils.keputran.BaseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<MitraResponseV1> getAllList() {
        List<MitraEntity> mitraList = mitraRepository.findAllByOrderByCreatedDateDesc();

        List<MitraResponseV1> responses = new ArrayList<>();
        for (MitraEntity mitra : mitraList) {
            responses.add(MitraResponseV1.builder()
                    .id(mitra.getId())
                    .name(mitra.getName())
                    .address(mitra.getAddress())
                    .type(mitra.getType())
                    .createdDate(mitra.getCreatedDate())
                    .build());
        }
        return responses;
    }

    @Override
    public Slice<MitraResponseV1> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<MitraEntity> mitraList = mitraRepository.findAllByOrderByCreatedDateDesc(pageable);

        List<MitraResponseV1> responses = new ArrayList<>();
        for (MitraEntity mitra : mitraList) {
            responses.add(MitraResponseV1.builder()
                    .id(mitra.getId())
                    .name(mitra.getName())
                    .address(mitra.getAddress())
                    .type(mitra.getType())
                    .createdDate(mitra.getCreatedDate())
                    .build());
        }
        return new SliceImpl<>(responses, pageable, mitraList.hasNext());
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
    public BaseResponse delete(String id) {
        MitraEntity mitra = mitraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mitra not found with id: " + id));
        mitraRepository.delete(mitra);

        return BaseResponse.builder()
                .success(true)
                .message("Mitra deleted")
                .build();
    }
}