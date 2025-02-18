package com.example.crudspringboot.controller.v1.impl;

import com.example.crudspringboot.base.response.DataResponseParameter;
import com.example.crudspringboot.base.response.ListResponseParameter;
import com.example.crudspringboot.base.response.ResponseHelper;
import com.example.crudspringboot.controller.advices.BaseControllerImpl;
import com.example.crudspringboot.controller.v1.FarmerControllerV1;
import com.example.crudspringboot.request.v1.FarmerRequestV1;
import com.example.crudspringboot.response.v1.FarmerResponseV1;
import com.example.crudspringboot.services.v1.FarmerServiceV1;
import lombok.RequiredArgsConstructor;

@BaseControllerImpl
@RequiredArgsConstructor
public class FarmerControllerImplV1 implements FarmerControllerV1 {
    private final FarmerServiceV1 farmerService;

    @Override
    public ListResponseParameter<FarmerResponseV1> index() {
        return ResponseHelper.createResponse(farmerService.index());
    }

    @Override
    public DataResponseParameter<FarmerResponseV1> store(FarmerRequestV1 req) {
        return ResponseHelper.createResponse(farmerService.store(req));
    }

    @Override
    public DataResponseParameter<FarmerResponseV1> show(String id) {
        return ResponseHelper.createResponse(farmerService.show(id));
    }

    @Override
    public DataResponseParameter<FarmerResponseV1> update(String id, FarmerRequestV1 req) {
        return ResponseHelper.createResponse(farmerService.update(id, req));
    }

    @Override
    public DataResponseParameter<FarmerResponseV1> delete(String id) {
        return ResponseHelper.createResponse(farmerService.delete(id));
    }
}
