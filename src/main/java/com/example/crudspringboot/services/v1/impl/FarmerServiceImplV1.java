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

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmerServiceImplV1 implements FarmerServiceV1 {
    private final FarmerRepository farmerRepository;
    private final MitraRepository mitraRepository;
    private final MessageLib messageLib;

    @Override
    public List<FarmerResponseV1> index() {
        List<FarmerEntity> farmers = farmerRepository.findAll();
        return farmers.stream().map(this::responses).toList();
    }

    @Override
    public FarmerResponseV1 store(FarmerRequestV1 request) {
        MitraEntity mitra = mitraRepository.findById(request.getMitraId())
                .orElseThrow(() -> new BadRequestException(messageLib.getMitraNotFound()));

        FarmerEntity farmer = new FarmerEntity();
        farmer.setName(request.getName());
        farmer.setAddress(request.getAddress());
        farmer.setMitra(mitra);

        FarmerEntity saved = farmerRepository.save(farmer);
        return responses(saved);
    }

    private FarmerResponseV1 responses(FarmerEntity entity) {
        return FarmerResponseV1.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .mitra(FarmerResponseV1.MitraSimpleResponse.builder()
                        .id(entity.getMitra().getId())
                        .name(entity.getMitra().getName())
                        .build())
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }
}
