package com.example.crudspringboot.maxxitaniapps.services.v1.impl;

import com.example.crudspringboot.core.utils.exceptions.NotFoundException;
import com.example.crudspringboot.core.utils.message.MessageLib;
import com.example.crudspringboot.core.utils.validation.Validate;
import com.example.crudspringboot.maxxitaniapps.repositories.FarmerRepository;
import com.example.crudspringboot.maxxitaniapps.repositories.MitraRepository;
import com.example.crudspringboot.maxxitaniapps.repositories.entities.FarmerEntity;
import com.example.crudspringboot.maxxitaniapps.repositories.entities.MitraEntity;
import com.example.crudspringboot.maxxitaniapps.request.v1.FarmerRequestV1;
import com.example.crudspringboot.maxxitaniapps.response.v1.FarmerResponseV1;
import com.example.crudspringboot.maxxitaniapps.services.v1.FarmerServiceV1;
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
public class FarmerServiceImplV1 implements FarmerServiceV1 {
    private final FarmerRepository farmerRepository;
    private final MitraRepository mitraRepository;
    private final MessageLib messageLib;

    @Override
    public List<FarmerResponseV1> getListFarmer() {
        List<FarmerEntity> farmers = farmerRepository.findAllByOrderByCreatedDateDesc();
        List<FarmerResponseV1> responses = new ArrayList<>();
        for (FarmerEntity farmer : farmers) {
            responses.add(mapFarmerToResponse(farmer));
        }
        return responses;
    }

    private FarmerEntity setFarmerInDatabase(FarmerRequestV1 req) {
        Validate.c(req, Map.of(
                messageLib.getFarmerNameNotFound(), FarmerRequestV1::getFarmer_name,
                messageLib.getFarmerPhoneNotFound(), FarmerRequestV1::getFarmer_phone,
                messageLib.getFarmerAddressNotFound(), FarmerRequestV1::getFarmer_address
        ));

        MitraEntity mit = setMitraRelationID(req);

        FarmerEntity farmer = new FarmerEntity();
        farmer.setFarmer_code(req.getFarmer_code() != null ? req.getFarmer_code() : generateRandomCode());
        farmer.setFarmer_name(req.getFarmer_name());
        farmer.setFarmer_phone(req.getFarmer_phone());
        farmer.setFarmer_address(req.getFarmer_address());
        farmer.setMitra(mit);
        farmer.setCreatedBy(getCurentUser());
        farmer.setCreatedDate(getCreatedDate());

        return farmerRepository.save(farmer);
    }

    @Override
    public FarmerResponseV1 createFarmer(FarmerRequestV1 req) {
        return mapFarmerToResponse(setFarmerInDatabase(req));
    }

    @Override
    public FarmerResponseV1 detailFarmer(String id) {
        return mapFarmerToResponse(findFarmerById(id));
    }

    @Override
    public FarmerResponseV1 updateFarmer(String id, FarmerRequestV1 req) {
        return mapFarmerToResponse(setMitraUpdateInDatabase(id, req));
    }

    @Override
    public FarmerResponseV1 deleteFarmer(String id) {
        return mapFarmerToResponse(setSoftDeleteFarmer(id));
    }

    @Override
    public Slice<FarmerResponseV1> getFarmerActive(Pageable pageable) {
        Slice<FarmerEntity> farmerList = farmerRepository.findAllByActiveTrueOrderByCreatedDateDesc(pageable);
        List<FarmerResponseV1> responses = new ArrayList<>();

        for (FarmerEntity farmer : farmerList) {
            responses.add(mapFarmerToResponse(farmer));
        }

        return new SliceImpl<>(responses, pageable, farmerList.hasNext());
    }

    @Override
    public Slice<FarmerResponseV1> getFarmerInActive(Pageable pageable) {
        Slice<FarmerEntity> farmerList = farmerRepository.findAllByActiveFalseOrderByCreatedDateDesc(pageable);
        List<FarmerResponseV1> responses = new ArrayList<>();

        for (FarmerEntity farmer : farmerList) {
            responses.add(mapFarmerToResponse(farmer));
        }

        return new SliceImpl<>(responses, pageable, farmerList.hasNext());
    }

    private FarmerResponseV1 mapFarmerToResponse(FarmerEntity entity) {
        return FarmerResponseV1.builder()
                .id(entity.getId())
                .farmer_code(entity.getFarmer_code())
                .farmer_name(entity.getFarmer_name())
                .farmer_phone(entity.getFarmer_phone())
                .farmer_address(entity.getFarmer_address())
                .mitra_id(entity.getMitra().getId()) // ini berupa string
                .mitra(FarmerResponseV1.MitraResponse.builder() // ini berupa object
                        .id(entity.getMitra().getId())
                        .mitra_name(entity.getMitra().getMitra_name())
                        .mitra_code(entity.getMitra().getMitra_code())
                .build())
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    private FarmerEntity findFarmerById(String id) {
        return farmerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageLib.getFarmerIdNotFound()));
    }

    private MitraEntity setMitraRelationID(FarmerRequestV1 req) {
        return mitraRepository.findById(req.getMitra_id())
                .orElseThrow(() -> new NotFoundException(messageLib.getMitraNotFound()));
    }

    private FarmerEntity setMitraUpdateInDatabase(String id, FarmerRequestV1 req) {
        FarmerEntity farmerById = findFarmerById(id);
        MitraEntity mitraById = setMitraRelationID(req);

        farmerById.setFarmer_name(req.getFarmer_name());
        farmerById.setFarmer_phone(req.getFarmer_phone());
        farmerById.setFarmer_address(req.getFarmer_address());
        farmerById.setMitra(mitraById);
        farmerById.setModifiedBy(getModifiedByUpdate());
        farmerById.setModifiedDate(getModifiedDate());

        return farmerRepository.save(farmerById);
    }

    private FarmerEntity setSoftDeleteFarmer(String id) {
        FarmerEntity farmerById = findFarmerById(id);

        farmerById.setDeletedDate(getModifiedDate());
        farmerById.setDeletedBy(getCurentUser());
        farmerById.setModifiedBy(getModifiedByDelete());
        farmerById.setActive(false);

        return farmerRepository.save(farmerById);
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
        int num1 = 1 + random.nextInt(9); // Angka pertama (1-9)
        int num2 = 100 + random.nextInt(900); // Angka kedua (100-999)

        return String.format("PTN-%d-%d", num1, num2);
    }

    private Date getModifiedDate() {
        return new Date();
    }

    private Date getCreatedDate() {
        return new Date();
    }

}
