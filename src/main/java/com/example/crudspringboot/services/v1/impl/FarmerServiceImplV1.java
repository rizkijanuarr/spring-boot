package com.example.crudspringboot.services.v1.impl;

import com.example.crudspringboot.base.exceptions.NotFoundException;
import com.example.crudspringboot.base.message.MessageLib;
import com.example.crudspringboot.base.validation.Validate;
import com.example.crudspringboot.repositories.FarmerRepository;
import com.example.crudspringboot.repositories.MitraRepository;
import com.example.crudspringboot.repositories.entities.FarmerEntity;
import com.example.crudspringboot.repositories.entities.MitraEntity;
import com.example.crudspringboot.request.v1.FarmerRequestV1;
import com.example.crudspringboot.response.v1.FarmerResponseV1;
import com.example.crudspringboot.services.v1.FarmerServiceV1;
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
    public List<FarmerResponseV1> index() {
        List<FarmerEntity> farmers = farmerRepository.findAllByOrderByCreatedDateDesc();
        List<FarmerResponseV1> responses = new ArrayList<>();
        for (FarmerEntity farmer : farmers) {
            responses.add(responses(farmer));
        }
        return responses;
    }

    @Override
    public FarmerResponseV1 store(FarmerRequestV1 req) {
        Validate.c(req, Map.of(
                messageLib.getFarmerNameNotFound(), FarmerRequestV1::getFarmer_name,
                messageLib.getFarmerPhoneNotFound(), FarmerRequestV1::getFarmer_phone,
                messageLib.getFarmerAddressNotFound(), FarmerRequestV1::getFarmer_address
        ));

        MitraEntity mit = mitra(req.getMitra_id());

        FarmerEntity farmer = new FarmerEntity();
        farmer.setFarmer_code(req.getFarmer_code() != null ? req.getFarmer_code() : generateRandomCode());
        farmer.setFarmer_name(req.getFarmer_name());
        farmer.setFarmer_phone(req.getFarmer_phone());
        farmer.setFarmer_address(req.getFarmer_address());
        farmer.setMitra(mit);
        farmer.setCreatedBy(getCurentUser());
        farmer.setCreatedDate(getCreatedDate());

        FarmerEntity created = farmerRepository.save(farmer);
        return responses(created);
    }

    @Override
    public FarmerResponseV1 show(String id) {
        FarmerEntity far = farmer(id);
        return responses(far);
    }

    @Override
    public FarmerResponseV1 update(String id, FarmerRequestV1 req) {
        FarmerEntity far = farmer(id);
        MitraEntity mit = mitra(id);

        far.setFarmer_name(req.getFarmer_name());
        far.setFarmer_phone(req.getFarmer_phone());
        far.setFarmer_address(req.getFarmer_address());
        far.setMitra(mit);
        far.setModifiedBy(getModifiedByUpdate());
        far.setModifiedDate(getModifiedDate());

        FarmerEntity updated = farmerRepository.save(far);
        return responses(updated);
    }

    @Override
    public FarmerResponseV1 delete(String id) {
        FarmerEntity far = farmer(id);

        far.setDeletedDate(getModifiedDate());
        far.setDeletedBy(getCurentUser());
        far.setModifiedBy(getModifiedByDelete());
        far.setActive(false);

        farmerRepository.save(far);
        return responses(far);
    }

    @Override
    public Slice<FarmerResponseV1> getFarmerActive(Pageable pageable) {
        Slice<FarmerEntity> farmerList = farmerRepository.findAllByActiveTrueOrderByCreatedDateDesc(pageable);
        List<FarmerResponseV1> responses = new ArrayList<>();

        for (FarmerEntity farmer : farmerList) {
            responses.add(responses(farmer));
        }

        return new SliceImpl<>(responses, pageable, farmerList.hasNext());
    }

    @Override
    public Slice<FarmerResponseV1> getFarmerInActive(Pageable pageable) {
        Slice<FarmerEntity> farmerList = farmerRepository.findAllByActiveFalseOrderByCreatedDateDesc(pageable);
        List<FarmerResponseV1> responses = new ArrayList<>();

        for (FarmerEntity farmer : farmerList) {
            responses.add(responses(farmer));
        }

        return new SliceImpl<>(responses, pageable, farmerList.hasNext());
    }

    private FarmerResponseV1 responses(FarmerEntity entity) {
        return FarmerResponseV1.builder()
                .id(entity.getId())
                .farmer_code(entity.getFarmer_code())
                .farmer_name(entity.getFarmer_name())
                .farmer_phone(entity.getFarmer_phone())
                .farmer_address(entity.getFarmer_address())
                .mitra(FarmerResponseV1.MitraResponse.builder()
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

    private FarmerEntity farmer(String id) {
        return farmerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageLib.getFarmerIdNotFound()));
    }

    private MitraEntity mitra(String id) {
        return mitraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageLib.getMitraNotFound()));
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
