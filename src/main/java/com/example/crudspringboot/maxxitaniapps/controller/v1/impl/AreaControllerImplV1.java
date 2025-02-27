package com.example.crudspringboot.maxxitaniapps.controller.v1.impl;

import com.example.crudspringboot.core.response.base.BaseResponse;
import com.example.crudspringboot.core.response.base.BaseResponseSlice;
import com.example.crudspringboot.core.response.base.ResponseHelper;
import com.example.crudspringboot.maxxitaniapps.controller.v1.AreaControllerV1;
import com.example.crudspringboot.maxxitaniapps.request.v1.AreaRequestV1;
import com.example.crudspringboot.maxxitaniapps.services.v1.AreaServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class AreaControllerImplV1 implements AreaControllerV1 {

    private final AreaServiceV1 areaServiceV1;

    @Override
    public ResponseEntity<BaseResponse> getListArea() {
        return ResponseHelper.buildOkResponse(areaServiceV1.getListArea());
    }

    @Override
    public ResponseEntity<BaseResponse> createArea(AreaRequestV1 req) {
        return ResponseHelper.buildOkResponse(areaServiceV1.createArea(req));
    }

    @Override
    public ResponseEntity<BaseResponse> detailArea(String id) {
        return ResponseHelper.buildOkResponse(areaServiceV1.detailArea(id));
    }

    @Override
    public ResponseEntity<BaseResponse> updateArea(String id, AreaRequestV1 req) {
        return ResponseHelper.buildOkResponse(areaServiceV1.updateArea(id, req));
    }

    @Override
    public ResponseEntity<BaseResponse> deleteArea(String id) {
        return ResponseHelper.buildOkResponse(areaServiceV1.deleteArea(id));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getAreaActive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(areaServiceV1.getAreaActive(pageable));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getAreaInActive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(areaServiceV1.getAreaInActive(pageable));
    }
}
