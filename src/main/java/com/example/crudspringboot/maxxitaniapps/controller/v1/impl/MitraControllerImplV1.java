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
    public ResponseEntity<BaseResponse> getListMitra() {
        return ResponseHelper.buildOkResponse(mitraService.getListMitra());
    }

    @Override
    public ResponseEntity<BaseResponse> createMitra(MitraRequestV1 req) {
        return ResponseHelper.buildOkResponse(mitraService.createMitra(req));
    }

    @Override
    public ResponseEntity<BaseResponse> detailMitra(String id) {
        String requester = httpServletRequest.getAttribute(ConstantHeader.HEADER_X_ID).toString();
        return ResponseHelper.buildOkResponse(mitraService.detailMitra(id, requester));
    }

    @Override
    public ResponseEntity<BaseResponse> updateMitra(String id, MitraRequestV1 req) {
        return ResponseHelper.buildOkResponse(mitraService.updateMitra(id, req));
    }

    @Override
    public ResponseEntity<BaseResponse> deleteMitra(String id) {
        return ResponseHelper.buildOkResponse(mitraService.deleteMitra(id));
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