package com.example.crudspringboot.maxxitaniapps.controller.v1.impl;

import com.example.crudspringboot.core.response.base.BaseResponse;
import com.example.crudspringboot.core.response.base.BaseResponseSlice;
import com.example.crudspringboot.core.response.base.ResponseHelper;
import com.example.crudspringboot.maxxitaniapps.controller.v1.FarmerControllerV1;
import com.example.crudspringboot.maxxitaniapps.request.v1.FarmerRequestV1;
import com.example.crudspringboot.maxxitaniapps.services.v1.FarmerServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class FarmerControllerImplV1 implements FarmerControllerV1 {

    private final FarmerServiceV1 farmerService;

    @Override
    public ResponseEntity<BaseResponse> getListFarmer() {
        return ResponseHelper.buildOkResponse(farmerService.getListFarmer());
    }

    @Override
    public ResponseEntity<BaseResponse> createFarmer(FarmerRequestV1 req) {
        return ResponseHelper.buildOkResponse(farmerService.createFarmer(req));
    }

    @Override
    public ResponseEntity<BaseResponse> detailFarmer(String id) {
        return ResponseHelper.buildOkResponse(farmerService.detailFarmer(id));
    }

    @Override
    public ResponseEntity<BaseResponse> updateFarmer(String id, FarmerRequestV1 req) {
        return ResponseHelper.buildOkResponse(farmerService.updateFarmer(id, req));
    }

    @Override
    public ResponseEntity<BaseResponse> deleteFarmer(String id) {
        return ResponseHelper.buildOkResponse(farmerService.deleteFarmer(id));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getFarmerActive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(farmerService.getFarmerActive(pageable));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getFarmerInActive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(farmerService.getFarmerInActive(pageable));
    }
}
