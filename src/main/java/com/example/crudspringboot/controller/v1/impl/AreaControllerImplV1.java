package com.example.crudspringboot.controller.v1.impl;

import com.example.crudspringboot.base.response.BaseResponse;
import com.example.crudspringboot.base.response.BaseResponseSlice;
import com.example.crudspringboot.base.response.ResponseHelper;
import com.example.crudspringboot.controller.v1.AreaControllerV1;
import com.example.crudspringboot.request.v1.AreaRequestV1;
import com.example.crudspringboot.services.v1.AreaServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AreaControllerImplV1 implements AreaControllerV1 {

    @Autowired
    private AreaServiceV1 areaServiceV1;

    @Override
    public ResponseEntity<BaseResponse> index() {
        return ResponseHelper.buildOkResponse(areaServiceV1.index());
    }

    @Override
    public ResponseEntity<BaseResponse> store(AreaRequestV1 req) {
        return ResponseHelper.buildOkResponse(areaServiceV1.store(req));
    }

    @Override
    public ResponseEntity<BaseResponse> show(String id) {
        return ResponseHelper.buildOkResponse(areaServiceV1.show(id));
    }

    @Override
    public ResponseEntity<BaseResponse> update(String id, AreaRequestV1 req) {
        return ResponseHelper.buildOkResponse(areaServiceV1.update(id, req));
    }

    @Override
    public ResponseEntity<BaseResponse> delete(String id) {
        return ResponseHelper.buildOkResponse(areaServiceV1.delete(id));
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
