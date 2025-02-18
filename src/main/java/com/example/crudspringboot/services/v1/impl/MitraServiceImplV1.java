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
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MitraServiceImplV1 implements MitraServiceV1 {
    private final MitraRepository mitraRepository;
    private final MessageLib messageLib;

    @Override
    public List<MitraResponseV1> index() {
        List<MitraEntity> mitras = mitraRepository.findAllByOrderByCreatedDateDesc();
        List<MitraResponseV1> responses = new ArrayList<>();
        for (MitraEntity mitra : mitras) {
            responses.add(responses(mitra));
        }
        return responses;
    }

    @Override
    public MitraResponseV1 store(MitraRequestV1 req) {
        MitraEntity mitra = new MitraEntity();
        mitra.setMitra_code(req.getMitra_code() != null ? req.getMitra_code() : generateRandomCode());
        mitra.setMitra_name(req.getMitra_name());
        mitra.setMitra_phone(req.getMitra_phone());
        mitra.setMitra_address(req.getMitra_address());
        mitra.setMitra_type(req.getMitra_type());

        MitraEntity created = mitraRepository.save(mitra);
        return responses(created);
    }

    @Override
    public MitraResponseV1 show(String id) {
        MitraEntity mitra = mitraRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getMitraNotFound()));
        return responses(mitra);
    }

    @Override
    public MitraResponseV1 update(String id, MitraRequestV1 req) {
        MitraEntity mitra = mitraRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getMitraNotFound()));

        mitra.setMitra_name(req.getMitra_name());
        mitra.setMitra_phone(req.getMitra_phone());
        mitra.setMitra_address(req.getMitra_address());
        mitra.setMitra_type(req.getMitra_type());
        mitra.setModifiedBy(getModifiedByUpdate());
        mitra.setModifiedDate(getModifiedDate());

        MitraEntity updated = mitraRepository.save(mitra);
        return responses(updated);
    }

    @Override
    public MitraResponseV1 delete(String id) {
        MitraEntity mitra = mitraRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getMitraNotFound()));

        mitra.setDeletedDate(getModifiedDate());
        mitra.setDeletedBy(getCurentUser());
        mitra.setModifiedBy(getModifiedByDelete());
        mitra.setActive(false);

        mitraRepository.save(mitra);
        return responses(mitra);
    }

    private MitraResponseV1 responses(MitraEntity entity) {
        return MitraResponseV1.builder()
                .id(entity.getId())
                .mitra_code(entity.getMitra_code())
                .mitra_name(entity.getMitra_name())
                .mitra_phone(entity.getMitra_phone())
                .mitra_address(entity.getMitra_address())
                .mitra_type(entity.getMitra_type())
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
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

    private String generateRandomCode() {
        Random random = new Random();
        int num1 = 900 + random.nextInt(100);
        int num2 = 1000 + random.nextInt(90000);
        int num3 = 100 + random.nextInt(900);
        return String.format("%d-%d-%d", num1, num2, num3);
    }

    private LocalDateTime getModifiedDate() {
        return LocalDateTime.now();
    }
}