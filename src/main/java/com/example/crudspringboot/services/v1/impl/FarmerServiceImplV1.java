package com.example.crudspringboot.services.v1.impl;

import com.example.crudspringboot.base.exceptions.BadRequestException;
import com.example.crudspringboot.base.message.MessageLib;
import com.example.crudspringboot.repositories.FarmerRepository;
import com.example.crudspringboot.repositories.MitraRepository;
import com.example.crudspringboot.repositories.entities.FarmerEntity;
import com.example.crudspringboot.repositories.entities.MitraEntity;
import com.example.crudspringboot.request.v1.FarmerRequestV1;
import com.example.crudspringboot.response.v1.FarmerResponseV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.services.v1.FarmerServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
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
        MitraEntity mitra = mitraRepository.findById(req.getMitra_id())
                .orElseThrow(() -> new BadRequestException(messageLib.getMitraNotFound()));

        FarmerEntity farmer = new FarmerEntity();
        farmer.setFarmer_code(req.getFarmer_code() != null ? req.getFarmer_code() : generateRandomCode());
        farmer.setFarmer_name(req.getFarmer_name());
        farmer.setFarmer_phone(req.getFarmer_phone());
        farmer.setFarmer_address(req.getFarmer_address());
        farmer.setMitra(mitra);

        FarmerEntity created = farmerRepository.save(farmer);
        return responses(created);
    }

    @Override
    public FarmerResponseV1 show(String id) {
        FarmerEntity farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getFarmerNotFound()));
        return responses(farmer);
    }

    @Override
    public FarmerResponseV1 update(String id, FarmerRequestV1 req) {
        FarmerEntity farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getFarmerNotFound()));
        MitraEntity mitra = mitraRepository.findById(req.getMitra_id())
                .orElseThrow(() -> new BadRequestException(messageLib.getMitraNotFound()));

        farmer.setFarmer_name(req.getFarmer_name());
        farmer.setFarmer_phone(req.getFarmer_phone());
        farmer.setFarmer_address(req.getFarmer_address());
        farmer.setMitra(mitra);
        farmer.setModifiedBy(getModifiedByUpdate());
        farmer.setModifiedDate(getModifiedDate());

        FarmerEntity updated = farmerRepository.save(farmer);
        return responses(updated);
    }

    @Override
    public FarmerResponseV1 delete(String id) {
        FarmerEntity farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getFarmerNotFound()));

        farmer.setDeletedDate(getModifiedDate());
        farmer.setDeletedBy(getCurentUser());
        farmer.setModifiedBy(getModifiedByDelete());
        farmer.setActive(false);

        farmerRepository.save(farmer);
        return responses(farmer);
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

    private LocalDateTime getModifiedDate() {
        return LocalDateTime.now();
    }

}
