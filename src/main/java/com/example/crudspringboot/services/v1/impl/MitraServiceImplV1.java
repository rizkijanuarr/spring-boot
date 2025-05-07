package com.example.crudspringboot.services.v1.impl;

import com.example.crudspringboot.repositories.UserRepository;
import com.example.crudspringboot.repositories.entities.UserEntity;
import com.example.crudspringboot.utils.exceptions.BadRequestException;
import com.example.crudspringboot.utils.exceptions.NotFoundException;
import com.example.crudspringboot.utils.message.MessageLib;
import com.example.crudspringboot.utils.validation.Validate;
import com.example.crudspringboot.repositories.MitraRepository;
import com.example.crudspringboot.repositories.entities.MitraEntity;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.services.v1.MitraServiceV1;
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
    public List<MitraResponseV1> getListMitra() {
        List<MitraEntity> mitras = mitraRepository.findAllByOrderByCreatedDateDesc();
        List<MitraResponseV1> responses = new ArrayList<>();
        for (MitraEntity mitra : mitras) {
            responses.add(mapMitraToResponse(mitra));
        }
        return responses;
    }

    @Override
    public MitraResponseV1 createMitra(MitraRequestV1 req) {
        Validate.c(req, Map.of(
                messageLib.getMitraNameCantNull(), MitraRequestV1::getMitra_name,
                messageLib.getMitraPhoneCantNull(), MitraRequestV1::getMitra_phone,
                messageLib.getMitraAddressCantNull(), MitraRequestV1::getMitra_address,
                messageLib.getMitraTypeCantNull(), MitraRequestV1::getMitra_type
        ));
        MitraEntity savedMitra = setMitraInDatabase(req);
        return mapMitraToResponse(savedMitra);
    }

    @Override
    public MitraResponseV1 detailMitra(String id, String requester) {
        getUser(requester);
        MitraEntity mitraById = findMitraById(id);
        return mapMitraToResponse(mitraById);
    }

    @Override
    public MitraResponseV1 updateMitra(String id, MitraRequestV1 req) {
        MitraEntity updated = setMitraUpdateInDatabase(id, req);
        return mapMitraToResponse(updated);
    }

    @Override
    public MitraResponseV1 deleteMitra(String id) {
        return mapMitraToResponse(setMitraSoftDelete(id));
    }

    public Slice<MitraResponseV1> getMitraActive(Pageable pageable) {
        Slice<MitraEntity> mitraList = mitraRepository.findAllByActiveTrueOrderByCreatedDateDesc(pageable);
        List<MitraResponseV1> responses = new ArrayList<>();

        for (MitraEntity mitra : mitraList) {
            responses.add(mapMitraToResponse(mitra));
        }

        return new SliceImpl<>(responses, pageable, mitraList.hasNext());
    }

    public Slice<MitraResponseV1> getMitraInActive(Pageable pageable) {
        Slice<MitraEntity> mitraList = mitraRepository.findAllByActiveFalseOrderByCreatedDateDesc(pageable);
        List<MitraResponseV1> responses = new ArrayList<>();

        for (MitraEntity mitra : mitraList) {
            responses.add(mapMitraToResponse(mitra));
        }

        return new SliceImpl<>(responses, pageable, mitraList.hasNext());
    }

    private MitraResponseV1 mapMitraToResponse(MitraEntity entity) {
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

    private MitraEntity setMitraSoftDelete(String id) {
        MitraEntity mitraById = findMitraById(id);

        mitraById.setDeletedDate(getModifiedDate());
        mitraById.setDeletedBy(getCurentUser());
        mitraById.setModifiedBy(getModifiedByDelete());
        mitraById.setActive(false);

        return mitraRepository.save(mitraById);
    }

    private MitraEntity setMitraUpdateInDatabase(String id, MitraRequestV1 req) {
        MitraEntity mitraById = findMitraById(id);

        mitraById.setMitra_name(req.getMitra_name());
        mitraById.setMitra_phone(req.getMitra_phone());
        mitraById.setMitra_address(req.getMitra_address());
        mitraById.setMitra_type(req.getMitra_type());
        mitraById.setModifiedBy(getModifiedByUpdate());
        mitraById.setModifiedDate(getModifiedDate());

        return mitraRepository.save(mitraById);
    }

    private MitraEntity setMitraInDatabase(MitraRequestV1 req) {

        MitraEntity mitra = new MitraEntity();
        mitra.setMitra_code(req.getMitra_code() != null ? req.getMitra_code() : generateRandomCode());
        mitra.setMitra_name(req.getMitra_name());
        mitra.setMitra_phone(req.getMitra_phone());
        mitra.setMitra_address(req.getMitra_address());
        mitra.setMitra_type(req.getMitra_type());
        mitra.setCreatedBy(getCurentUser());
        mitra.setCreatedDate(getCreatedDate());

        return mitraRepository.save(mitra);
    }

    private MitraEntity findMitraById(String id) {
        return mitraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageLib.getMitraNotFound()));
    }

    private UserEntity getUser(String requester) {
        return userRepository.findById(requester)
                .orElseThrow(() -> new BadRequestException(messageLib.getUserIdNotFound()));
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