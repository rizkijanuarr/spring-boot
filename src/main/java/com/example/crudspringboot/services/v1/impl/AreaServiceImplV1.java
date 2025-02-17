package com.example.crudspringboot.services.v1.impl;

import com.example.crudspringboot.base.exceptions.BadRequestException;
import com.example.crudspringboot.base.message.MessageLib;
import com.example.crudspringboot.repositories.AreaRepository;
import com.example.crudspringboot.repositories.FarmerRepository;
import com.example.crudspringboot.repositories.MitraRepository;
import com.example.crudspringboot.repositories.entities.AreaEntity;
import com.example.crudspringboot.repositories.entities.FarmerEntity;
import com.example.crudspringboot.repositories.entities.MitraEntity;
import com.example.crudspringboot.request.v1.AreaRequestV1;
import com.example.crudspringboot.response.v1.AreaResponseV1;
import com.example.crudspringboot.services.v1.AreaServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImplV1 implements AreaServiceV1 {
    private final FarmerRepository farmerRepository;
    private final AreaRepository areaRepository;
    private final MitraRepository mitraRepository;
    private final MessageLib messageLib;

    @Override
    public List<AreaResponseV1> index() {
        List<AreaEntity> areas = areaRepository.findAllByOrderByCreatedDateDesc();
        return areas.stream().map(this::responses).toList();
    }

    @Override
    public AreaResponseV1 store(AreaRequestV1 request) {
        MitraEntity mitra = mitraRepository.findById(request.getMitraId())
                .orElseThrow(() -> new BadRequestException(messageLib.getMitraNotFound()));
        FarmerEntity farmer = farmerRepository.findById(request.getFarmerId())
                .orElseThrow(() -> new BadRequestException(messageLib.getFarmerNotFound()));

        AreaEntity area = new AreaEntity();
        area.setName(request.getName());
        area.setWide(request.getWide());
        area.setMitra(mitra);
        area.setFarmer(farmer);

        AreaEntity saved = areaRepository.save(area);
        return responses(saved);
    }

    private AreaResponseV1 responses(AreaEntity entity) {
        return AreaResponseV1.builder()
                .id(entity.getId())
                .name(entity.getName())
                .wide(entity.getWide())
                .mitra(AreaResponseV1.MitraSimpleResponse.builder()
                        .id(entity.getMitra().getId())
                        .name(entity.getMitra().getName())
                        .build())
                .farmer(AreaResponseV1.FarmerSimpleResponse.builder()
                        .id(entity.getFarmer().getId())
                        .name(entity.getFarmer().getName())
                        .build())
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    private LocalDateTime getModifiedDate() {
        return LocalDateTime.now();
    }

    private String getModifiedByUpdate() {
        return "UPDATE";
    }

    private String getModifiedByDelete() {
        return "SOFT DELETE";
    }

    private String getCurentUser() {
        return "SYSTEM";
    }
}
