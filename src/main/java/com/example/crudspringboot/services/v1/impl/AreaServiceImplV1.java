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
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AreaServiceImplV1 implements AreaServiceV1 {
    private final AreaRepository areaRepository;
    private final FarmerRepository farmerRepository;
    private final MitraRepository mitraRepository;
    private final MessageLib messageLib;

    @Override
    public List<AreaResponseV1> index() {
        List<AreaEntity> areas = areaRepository.findAllByOrderByCreatedDateDesc();
        return areas.stream().map(this::responses).toList();
    }

    @Override
    public AreaResponseV1 store(AreaRequestV1 req) {
        FarmerEntity farmer = farmerRepository.findById(req.getFarmer_id())
                .orElseThrow(() -> new BadRequestException(messageLib.getFarmerNotFound()));

        AreaEntity areas = new AreaEntity();
        areas.setArea_name(req.getArea_name());
        areas.setArea_land(req.getArea_land());
        areas.setFarmer(farmer);

        AreaEntity created = areaRepository.save(areas);
        return responses(created);
    }

    @Override
    public AreaResponseV1 show(String id) {
        AreaEntity area = areaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getAreaNotFound()));
        return responses(area);
    }

    @Override
    public AreaResponseV1 update(String id, AreaRequestV1 req) {
        AreaEntity areas = areaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getAreaNotFound()));
        FarmerEntity farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getFarmerNotFound()));

        areas.setArea_name(req.getArea_name());
        areas.setArea_land(req.getArea_land());
        areas.setFarmer(farmer);
        farmer.setModifiedBy(getModifiedByUpdate());
        farmer.setModifiedDate(getModifiedDate());

        AreaEntity updated = areaRepository.save(areas);
        return responses(updated);
    }

    @Override
    public AreaResponseV1 delete(String id) {
        AreaEntity areas = areaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(messageLib.getAreaNotFound()));

        areas.setDeletedDate(getModifiedDate());
        areas.setDeletedBy(getCurentUser());
        areas.setModifiedBy(getModifiedByDelete());
        areas.setActive(false);

        areaRepository.save(areas);
        return responses(areas);
    }

    private AreaResponseV1 responses(AreaEntity entity) {
        return AreaResponseV1.builder()
                .id(entity.getId())
                .area_name(entity.getArea_name())
                .area_land(entity.getArea_land())
                .farmer(AreaResponseV1.FarmerResponse.builder()
                        .id(entity.getFarmer().getId())
                        .farmer_code(entity.getFarmer().getFarmer_code())
                        .farmer_name(entity.getFarmer().getFarmer_name())
                        .mitra(AreaResponseV1.MitraResponse.builder()
                                .id(entity.getFarmer().getMitra().getId())
                                .mitra_code(entity.getFarmer().getMitra().getMitra_code())
                                .mitra_name(entity.getFarmer().getMitra().getMitra_name())
                                .build()
                        )
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
