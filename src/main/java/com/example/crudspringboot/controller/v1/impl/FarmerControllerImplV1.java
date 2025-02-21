package com.example.crudspringboot.controller.v1.impl;

import com.example.crudspringboot.base.response.BaseResponse;
import com.example.crudspringboot.base.response.BaseResponseSlice;
import com.example.crudspringboot.base.response.ResponseHelper;
import com.example.crudspringboot.controller.v1.FarmerControllerV1;
import com.example.crudspringboot.request.v1.FarmerRequestV1;
import com.example.crudspringboot.services.v1.FarmerServiceV1;
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
    public ResponseEntity<BaseResponse> index() {
        return ResponseHelper.buildOkResponse(farmerService.index());
    }

    @Override
    public ResponseEntity<BaseResponse> store(FarmerRequestV1 req) {
        return ResponseHelper.buildOkResponse(farmerService.store(req));
    }

    @Override
    public ResponseEntity<BaseResponse> show(String id) {
        return ResponseHelper.buildOkResponse(farmerService.show(id));
    }

    @Override
    public ResponseEntity<BaseResponse> update(String id, FarmerRequestV1 req) {
        return ResponseHelper.buildOkResponse(farmerService.update(id, req));
    }

    @Override
    public ResponseEntity<BaseResponse> delete(String id) {
        return ResponseHelper.buildOkResponse(farmerService.delete(id));
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
