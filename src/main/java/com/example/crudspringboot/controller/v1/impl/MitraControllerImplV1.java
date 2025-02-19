package com.example.crudspringboot.controller.v1.impl;

import com.example.crudspringboot.base.response.DataResponseParameter;
import com.example.crudspringboot.base.response.ListResponseParameter;
import com.example.crudspringboot.base.response.ResponseHelper;
import com.example.crudspringboot.base.response.SliceResponseParameter;
import com.example.crudspringboot.controller.advices.BaseControllerImpl;
import com.example.crudspringboot.controller.v1.MitraControllerV1;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.services.v1.MitraServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@BaseControllerImpl
@RequiredArgsConstructor
public class MitraControllerImplV1 implements MitraControllerV1 {
    private final MitraServiceV1 mitraService;

    @Override
    public ListResponseParameter<MitraResponseV1> index() {
        return ResponseHelper.createResponse(mitraService.index());
    }

    @Override
    public DataResponseParameter<MitraResponseV1> store(MitraRequestV1 req) {
        return ResponseHelper.createResponse(mitraService.store(req));
    }

    @Override
    public DataResponseParameter<MitraResponseV1> show(String id) {
        return ResponseHelper.createResponse(mitraService.show(id));
    }

    @Override
    public DataResponseParameter<MitraResponseV1> update(String id, MitraRequestV1 req) {
        return ResponseHelper.createResponse(mitraService.update(id, req));
    }

    @Override
    public DataResponseParameter<MitraResponseV1> delete(String id) {
        return ResponseHelper.createResponse(mitraService.delete(id));
    }

    @Override
    public SliceResponseParameter<MitraResponseV1> getMitraActive(Pageable pageable) {
        return ResponseHelper.createResponse(mitraService.getMitraActive(pageable));
    }

    @Override
    public SliceResponseParameter<MitraResponseV1> getMitraInActive(Pageable pageable) {
        return ResponseHelper.createResponse(mitraService.getMitraInActive(pageable));
    }
}