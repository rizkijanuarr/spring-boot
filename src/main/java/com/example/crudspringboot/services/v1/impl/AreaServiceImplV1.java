package com.example.crudspringboot.services.v1.impl;

import com.example.crudspringboot.utils.exceptions.NotFoundException;
import com.example.crudspringboot.utils.message.MessageLib;
import com.example.crudspringboot.utils.validation.Validate;
import com.example.crudspringboot.repositories.AreaRepository;
import com.example.crudspringboot.repositories.FarmerRepository;
import com.example.crudspringboot.repositories.entities.AreaEntity;
import com.example.crudspringboot.repositories.entities.CoordinateEntity;
import com.example.crudspringboot.repositories.entities.FarmerEntity;
import com.example.crudspringboot.request.v1.AreaRequestV1;
import com.example.crudspringboot.response.v1.AreaResponseV1;
import com.example.crudspringboot.services.v1.AreaServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AreaServiceImplV1 implements AreaServiceV1 {
    private final AreaRepository areaRepository;
    private final FarmerRepository farmerRepository;
    private final MessageLib messageLib;

    @Override
    public List<AreaResponseV1> getListArea() {
        List<AreaEntity> areas = areaRepository.findAllByOrderByCreatedDateDesc();
        List<AreaResponseV1> responses = new ArrayList<>();
        for (AreaEntity area : areas) {
            responses.add(mapAreaToResponse(area));
        }
        return responses;
    }

    @Override
    public AreaResponseV1 createArea(AreaRequestV1 req) {
        AreaEntity areaCreate = setAreaInDatabase(req);
        return mapAreaToResponse(areaCreate);
    }

    @Override
    public AreaResponseV1 detailArea(String id) {
        AreaEntity areaById = findAreaById(id);
        return mapAreaToResponse(areaById);
    }

    @Override
    public AreaResponseV1 updateArea(String id, AreaRequestV1 req) {
        return mapAreaToResponse(setAreaUpdateInDatabase(id, req));
    }

    @Override
    public AreaResponseV1 deleteArea(String id) {
        AreaEntity deletedArea = setSoftDeleteAreaInDatabase(id);
        return mapAreaToResponse(deletedArea);
    }

    public Slice<AreaResponseV1> getAreaActive(Pageable pageable) {
        Slice<AreaEntity> areaList = areaRepository.findAllByActiveTrueOrderByCreatedDateDesc(pageable);
        List<AreaResponseV1> responses = new ArrayList<>();

        for (AreaEntity area : areaList) {
            responses.add(mapAreaToResponse(area));
        }

        return new SliceImpl<>(responses, pageable, areaList.hasNext());
    }

    public Slice<AreaResponseV1> getAreaInActive(Pageable pageable) {
        Slice<AreaEntity> areaList = areaRepository.findAllByActiveFalseOrderByCreatedDateDesc(pageable);
        List<AreaResponseV1> responses = new ArrayList<>();

        for (AreaEntity area : areaList) {
            responses.add(mapAreaToResponse(area));
        }

        return new SliceImpl<>(responses, pageable, areaList.hasNext());
    }

    private AreaEntity setAreaUpdateInDatabase(String id, AreaRequestV1 req) {
        AreaEntity areaById = findAreaById(id);
        FarmerEntity farmerById = setFarmerRelationID(req.getFarmer_id());

        areaById.setArea_name(req.getArea_name());
        areaById.setArea_land(req.getArea_land());
        areaById.setFarmer(farmerById);
        areaById.setModifiedBy(getModifiedByUpdate());
        areaById.setModifiedDate(getModifiedDate());

        return areaRepository.save(areaById);
    }

    private AreaEntity setSoftDeleteAreaInDatabase(String id) {
        AreaEntity areaById = findAreaById(id);

        areaById.setDeletedDate(getModifiedDate());
        areaById.setDeletedBy(getCurentUser());
        areaById.setModifiedBy(getModifiedByDelete());
        areaById.setActive(false);

        return areaRepository.save(areaById);
    }

    private AreaResponseV1 mapAreaToResponse(AreaEntity entity) {
        return AreaResponseV1.builder()
                .area_id(entity.getId())
                .farmer_id(entity.getFarmer().getId())
                .farmer_response(AreaResponseV1.FarmerResponse.builder()
                        .id(entity.getFarmer().getId())
                        .farmer_code(entity.getFarmer().getFarmer_code())
                        .farmer_name(entity.getFarmer().getFarmer_name())
                        .build())
                .mitra_profile_response(AreaResponseV1.MitraProfileResponse.builder()
                        .id(entity.getFarmer().getMitra().getId())
                        .mitra_code(entity.getFarmer().getMitra().getMitra_code())
                        .mitra_name(entity.getFarmer().getMitra().getMitra_name())
                        .mitra_address(entity.getFarmer().getMitra().getMitra_address())
                        .mitra_type(entity.getFarmer().getMitra().getMitra_type())
                        .build())
                .coordinate(entity.getCoordinates().stream()
                        .map(this::mapToCoordinates)
                        .collect(Collectors.toList()))
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    private AreaResponseV1.Coordinate mapToCoordinates(CoordinateEntity coordinate) {
        return AreaResponseV1.Coordinate.builder()
                .seq(coordinate.getSeq())
                .lat(coordinate.getLat())
                .lng(coordinate.getLng())
                .build();
    }

    private AreaEntity setAreaInDatabase(AreaRequestV1 req) {
        Validate.c(req, Map.of(
                messageLib.getAreaNameCantNull(), AreaRequestV1::getArea_name,
                messageLib.getAreaLandCantNull(), AreaRequestV1::getArea_land,
                messageLib.getFarmerIdNotFound(), AreaRequestV1::getFarmer_id,
                messageLib.getAreaCoordinatesCantNull(), AreaRequestV1::getCoordinates
        ));
        FarmerEntity farmerById = setFarmerRelationID(req.getFarmer_id());

        AreaEntity areas = new AreaEntity();
        areas.setArea_name(req.getArea_name());
        areas.setArea_land(req.getArea_land());
        areas.setFarmer(farmerById);
        areas.setCreatedBy(getCurentUser());
        areas.setCreatedDate(getCreatedDate());

        List<CoordinateEntity> coordinates = new ArrayList<>();

        if (req.getCoordinates() != null) {
            for (AreaRequestV1.CoordinatesReq coordReq : req.getCoordinates()) {
                CoordinateEntity coordinate = new CoordinateEntity();
                coordinate.setSeq(coordReq.getSeq());
                coordinate.setLat(coordReq.getLat());
                coordinate.setLng(coordReq.getLng());
                coordinate.setArea(areas);
                coordinate.setCreatedBy(areas.getCreatedBy());
                coordinate.setCreatedDate(areas.getCreatedDate());

                coordinates.add(coordinate);
            }
        }
        areas.setCoordinates(coordinates);
        return areaRepository.save(areas);
    }

    private AreaEntity findAreaById(String id) {
        return areaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageLib.getAreaNotFound()));
    }

    private FarmerEntity setFarmerRelationID(String id) {
        return farmerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageLib.getFarmerIdNotFound()));
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
