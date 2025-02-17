package com.example.crudspringboot.services.v1.impl;

import com.example.crudspringboot.base.message.MessageLib;
import com.example.crudspringboot.base.exceptions.BadRequestException;
import com.example.crudspringboot.base.validation.Validate;
import com.example.crudspringboot.repositories.MitraRepository;
import com.example.crudspringboot.repositories.entities.MitraEntity;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.services.v1.MitraServiceV1;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MitraServiceImplV1 implements MitraServiceV1 {
    private final MitraRepository mitraRepository;
    private final MessageLib messageLib;

    @Override
    public MitraResponseV1 create(MitraRequestV1 request) {
        Validate.c(request, Map.of(
                messageLib.getMitraNameCantNull(), MitraRequestV1::getName,
                messageLib.getMitraAddressCantNull(), MitraRequestV1::getAddress,
                messageLib.getMitraTypeCantNull(), MitraRequestV1::getType
        ));

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
                .orElseThrow(() -> new BadRequestException(messageLib.getMitraNotFound()));

        return MitraResponseV1.builder()
                .id(mitra.getId())
                .name(mitra.getName())
                .address(mitra.getAddress())
                .type(mitra.getType())
                .active(mitra.getActive())
                .createdDate(mitra.getCreatedDate())
                .modifiedDate(mitra.getModifiedDate())
                .modifiedBy(mitra.getModifiedBy())
                .deletedDate(mitra.getDeletedDate())
                .deletedBy(mitra.getDeletedBy())
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
                    .active(mitra.getActive())
                    .createdDate(mitra.getCreatedDate())
                    .modifiedDate(mitra.getModifiedDate())
                    .modifiedBy(mitra.getModifiedBy())
                    .deletedDate(mitra.getDeletedDate())
                    .deletedBy(mitra.getDeletedBy())
                    .build());
        }
        return responses;
    }

    @Override
    public Slice<MitraResponseV1> getAll(Pageable pageable) {
        Slice<MitraEntity> mitraList = mitraRepository.findAllByOrderByCreatedDateDesc(pageable);

        List<MitraResponseV1> responses = new ArrayList<>();
        for (MitraEntity mitra : mitraList) {
            responses.add(MitraResponseV1.builder()
                    .id(mitra.getId())
                    .name(mitra.getName())
                    .address(mitra.getAddress())
                    .type(mitra.getType())
                    .active(mitra.getActive())
                    .createdDate(mitra.getCreatedDate())
                    .modifiedDate(mitra.getModifiedDate())
                    .modifiedBy(mitra.getModifiedBy())
                    .deletedDate(mitra.getDeletedDate())
                    .deletedBy(mitra.getDeletedBy())
                    .build());
        }
        return new SliceImpl<>(responses, pageable, mitraList.hasNext());
    }

    @Override
    public MitraResponseV1 update(String id, MitraRequestV1 request) {
        MitraEntity mitra = mitraRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getMitraNotFound()));

        mitra.setName(request.getName());
        mitra.setAddress(request.getAddress());
        mitra.setType(request.getType());
        mitra.setModifiedBy(getModifiedByUpdate());
        mitra.setModifiedDate(LocalDateTime.now());

        MitraEntity updated = mitraRepository.save(mitra);

        return MitraResponseV1.builder()
                .id(updated.getId())
                .name(updated.getName())
                .address(updated.getAddress())
                .type(updated.getType())
                .createdDate(mitra.getCreatedDate())
                .modifiedDate(mitra.getModifiedDate())
                .modifiedBy(mitra.getModifiedBy())
                .build();
    }

    @Override
    public MitraResponseV1 delete(String id) {
        MitraEntity mitra = mitraRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getMitraNotFound()));

        mitra.setDeletedDate(LocalDateTime.now()); // Menandai kapan data dihapus
        mitra.setDeletedBy(getCurentUser()); // Atau ambil dari user yang login
        mitra.setModifiedBy(getModifiedByDelete()); // Set modifiedDate juga
        mitra.setActive(false); // Menonaktifkan entitas, agar tidak muncul dalam query normal

        mitraRepository.save(mitra);

        return MitraResponseV1.builder()
                .id(mitra.getId())
                .name(mitra.getName())
                .address(mitra.getAddress())
                .type(mitra.getType())
                .createdDate(mitra.getCreatedDate())
                .modifiedBy(mitra.getModifiedBy())
                .deletedBy(mitra.getDeletedBy())
                .deletedDate(mitra.getDeletedDate())
                .active(mitra.getActive())
                .build();
    }

    private String getCurentUser() {
        return "SYSTEM";
    }

    private String getModifiedByDelete() {
        return "SOFT DELETE";
    }

    private String getModifiedByUpdate() {
        return "UPDATE";
    }
}