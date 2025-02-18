package com.example.crudspringboot.controller.v1.impl;

import com.example.crudspringboot.base.response.DataResponseParameter;
import com.example.crudspringboot.base.response.ListResponseParameter;
import com.example.crudspringboot.base.response.ResponseHelper;
import com.example.crudspringboot.controller.advices.BaseControllerImpl;
import com.example.crudspringboot.controller.v1.AreaControllerV1;
import com.example.crudspringboot.request.v1.AreaRequestV1;
import com.example.crudspringboot.request.v1.FarmerRequestV1;
import com.example.crudspringboot.response.v1.AreaResponseV1;
import com.example.crudspringboot.response.v1.FarmerResponseV1;
import com.example.crudspringboot.services.v1.AreaServiceV1;
import com.example.crudspringboot.services.v1.FarmerServiceV1;
import lombok.RequiredArgsConstructor;

@BaseControllerImpl
@RequiredArgsConstructor
public class AreaControllerImplV1 implements AreaControllerV1 {
    private final AreaServiceV1 areaServiceV1;

    @Override
    public ListResponseParameter<AreaResponseV1> index() {
        return ResponseHelper.createResponse(areaServiceV1.index());
    }

    @Override
    public DataResponseParameter<AreaResponseV1> store(AreaRequestV1 req) {
        return ResponseHelper.createResponse(areaServiceV1.store(req));
    }

    @Override
    public DataResponseParameter<AreaResponseV1> show(String id) {
        return ResponseHelper.createResponse(areaServiceV1.show(id));
    }

    @Override
    public DataResponseParameter<AreaResponseV1> update(String id, AreaRequestV1 req) {
        return ResponseHelper.createResponse(areaServiceV1.update(id, req));
    }

    @Override
    public DataResponseParameter<AreaResponseV1> delete(String id) {
        return ResponseHelper.createResponse(areaServiceV1.delete(id));
    }
}
