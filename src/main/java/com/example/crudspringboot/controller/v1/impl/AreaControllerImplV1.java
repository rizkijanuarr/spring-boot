package com.example.crudspringboot.controller.v1.impl;

import com.example.crudspringboot.controller.advices.BaseControllerImpl;
import com.example.crudspringboot.response.base.BaseResponse;
import com.example.crudspringboot.response.base.BaseResponseSlice;
import com.example.crudspringboot.response.base.ResponseHelper;
import com.example.crudspringboot.controller.v1.AreaControllerV1;
import com.example.crudspringboot.request.v1.AreaRequestV1;
import com.example.crudspringboot.services.v1.AreaServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@BaseControllerImpl
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
