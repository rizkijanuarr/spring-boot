package com.example.crudspringboot.maxxitaniapps.services.v1.impl;

import com.example.crudspringboot.core.repositories.UserRepository;
import com.example.crudspringboot.core.repositories.entities.UserEntity;
import com.example.crudspringboot.core.utils.exceptions.BadRequestException;
import com.example.crudspringboot.core.utils.exceptions.NotFoundException;
import com.example.crudspringboot.core.utils.message.MessageLib;
import com.example.crudspringboot.core.utils.validation.Validate;
import com.example.crudspringboot.maxxitaniapps.repositories.MitraRepository;
import com.example.crudspringboot.maxxitaniapps.repositories.entities.MitraEntity;
import com.example.crudspringboot.maxxitaniapps.request.v1.MitraRequestV1;
import com.example.crudspringboot.maxxitaniapps.response.v1.MitraResponseV1;
import com.example.crudspringboot.maxxitaniapps.services.v1.MitraServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MitraServiceImplV1 implements MitraServiceV1 {
    private final MitraRepository mitraRepository;
    private final MessageLib messageLib;
    private final UserRepository userRepository;

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
        Validate.c(req, Map.of(
                messageLib.getMitraNameCantNull(), MitraRequestV1::getMitra_name,
                messageLib.getMitraPhoneCantNull(), MitraRequestV1::getMitra_phone,
                messageLib.getMitraAddressCantNull(), MitraRequestV1::getMitra_address,
                messageLib.getMitraTypeCantNull(), MitraRequestV1::getMitra_type
        ));

        MitraEntity mitra = new MitraEntity();
        mitra.setMitra_code(req.getMitra_code() != null ? req.getMitra_code() : generateRandomCode());
        mitra.setMitra_name(req.getMitra_name());
        mitra.setMitra_phone(req.getMitra_phone());
        mitra.setMitra_address(req.getMitra_address());
        mitra.setMitra_type(req.getMitra_type());
        mitra.setCreatedBy(getCurentUser());
        mitra.setCreatedDate(getCreatedDate());

        MitraEntity created = mitraRepository.save(mitra);
        return responses(created);
    }

    private MitraEntity mitra(String id) {
        return mitraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageLib.getMitraNotFound()));
    }

    private UserEntity getUser(String requester) {
        return userRepository.findById(requester)
                .orElseThrow(() -> new BadRequestException(messageLib.getUserIdNotFound()));
    }

    @Override
    public MitraResponseV1 show(String id, String requester) {
        getUser(requester);
        MitraEntity mit = mitra(id);
        return responses(mit);
    }

    @Override
    public MitraResponseV1 update(String id, MitraRequestV1 req) {
        MitraEntity mit = mitra(id);

        mit.setMitra_name(req.getMitra_name());
        mit.setMitra_phone(req.getMitra_phone());
        mit.setMitra_address(req.getMitra_address());
        mit.setMitra_type(req.getMitra_type());
        mit.setModifiedBy(getModifiedByUpdate());
        mit.setModifiedDate(getModifiedDate());

        MitraEntity updated = mitraRepository.save(mit);
        return responses(updated);
    }

    @Override
    public MitraResponseV1 delete(String id) {
        MitraEntity mit = mitra(id);

        mit.setDeletedDate(getModifiedDate());
        mit.setDeletedBy(getCurentUser());
        mit.setModifiedBy(getModifiedByDelete());
        mit.setActive(false);

        mitraRepository.save(mit);
        return responses(mit);
    }

    public Slice<MitraResponseV1> getMitraActive(Pageable pageable) {
        Slice<MitraEntity> mitraList = mitraRepository.findAllByActiveTrueOrderByCreatedDateDesc(pageable);
        List<MitraResponseV1> responses = new ArrayList<>();

        for (MitraEntity mitra : mitraList) {
            responses.add(responses(mitra));
        }

        return new SliceImpl<>(responses, pageable, mitraList.hasNext());
    }

    public Slice<MitraResponseV1> getMitraInActive(Pageable pageable) {
        Slice<MitraEntity> mitraList = mitraRepository.findAllByActiveFalseOrderByCreatedDateDesc(pageable);
        List<MitraResponseV1> responses = new ArrayList<>();

        for (MitraEntity mitra : mitraList) {
            responses.add(responses(mitra));
        }

        return new SliceImpl<>(responses, pageable, mitraList.hasNext());
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

    private Date getModifiedDate() {
        return new Date();
    }

    private Date getCreatedDate() {
        return new Date();
    }
}