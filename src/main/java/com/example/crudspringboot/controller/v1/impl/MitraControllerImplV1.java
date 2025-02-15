package com.example.crudspringboot.controller.v1.impl;

import com.example.crudspringboot.controller.advices.BaseControllerImpl;
import com.example.crudspringboot.controller.v1.MitraControllerV1;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.services.v1.MitraServiceV1;
import com.example.crudspringboot.utils.ApiResponse;
import com.example.crudspringboot.utils.keputran.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@BaseControllerImpl
@RequiredArgsConstructor
public class MitraControllerImplV1 implements MitraControllerV1 {
    private final MitraServiceV1 mitraService;

    @Override
    public DataResponseParameter<MitraResponseV1> create(MitraRequestV1 request) {
        return ResponseHelper.createResponse(mitraService.create(request));
    }

    @Override
    public DataResponseParameter<MitraResponseV1> getById(String id) {
        return ResponseHelper.createResponse(mitraService.getById(id));
    }

    @Override
    public ListResponseParameter<MitraResponseV1> getAllList() {
        return ResponseHelper.createResponse(mitraService.getAllList());
    }

    @Override
    public SliceResponseParameter<MitraResponseV1> getAll(Integer page, Integer size) {
        return ResponseHelper.createResponse(mitraService.getAll(page, size));
    }

    @Override
    public DataResponseParameter<MitraResponseV1> update(String id, MitraRequestV1 request) {
        return ResponseHelper.createResponse(mitraService.update(id, request));
    }

    @Override
    public BaseResponse delete(String id) {
        return ResponseHelper.createBaseResponse(mitraService.delete(id));
    }
}