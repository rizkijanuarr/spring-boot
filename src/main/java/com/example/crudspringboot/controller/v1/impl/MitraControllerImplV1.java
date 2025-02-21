package com.example.crudspringboot.controller.v1.impl;

import com.example.crudspringboot.base.response.BaseResponse;
import com.example.crudspringboot.base.response.BaseResponseSlice;
import com.example.crudspringboot.base.response.ResponseHelper;
import com.example.crudspringboot.controller.v1.MitraControllerV1;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.services.v1.MitraServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class MitraControllerImplV1 implements MitraControllerV1 {

    private MitraServiceV1 mitraService;

    @Override
    public ResponseEntity<BaseResponse> index() {
        return ResponseHelper.buildOkResponse(mitraService.index());
    }

    @Override
    public ResponseEntity<BaseResponse> store(MitraRequestV1 req) {
        return ResponseHelper.buildOkResponse(mitraService.store(req));
    }

    @Override
    public ResponseEntity<BaseResponse> show(String id) {
        return ResponseHelper.buildOkResponse(mitraService.show(id));
    }

    @Override
    public ResponseEntity<BaseResponse> update(String id, MitraRequestV1 req) {
        return ResponseHelper.buildOkResponse(mitraService.update(id, req));
    }

    @Override
    public ResponseEntity<BaseResponse> delete(String id) {
        return ResponseHelper.buildOkResponse(mitraService.delete(id));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getMitraActive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(mitraService.getMitraActive(pageable));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getMitraInActive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(mitraService.getMitraInActive(pageable));
    }
}