package com.example.crudspringboot.maxxitaniapps.controller.v1.impl;

import com.example.crudspringboot.core.configs.constant.ConstantHeader;
import com.example.crudspringboot.core.response.base.BaseResponse;
import com.example.crudspringboot.core.response.base.BaseResponseSlice;
import com.example.crudspringboot.core.response.base.ResponseHelper;
import com.example.crudspringboot.maxxitaniapps.controller.v1.MitraControllerV1;
import com.example.crudspringboot.maxxitaniapps.request.v1.MitraRequestV1;
import com.example.crudspringboot.maxxitaniapps.services.v1.MitraServiceV1;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class MitraControllerImplV1 implements MitraControllerV1 {

    private final MitraServiceV1 mitraService;
    private final HttpServletRequest httpServletRequest;

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
        String requester = httpServletRequest.getAttribute(ConstantHeader.HEADER_X_ID).toString();
        return ResponseHelper.buildOkResponse(mitraService.show(id, requester));
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