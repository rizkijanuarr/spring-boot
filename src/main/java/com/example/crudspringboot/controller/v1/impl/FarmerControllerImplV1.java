package com.example.crudspringboot.controller.v1.impl;

import com.example.crudspringboot.base.response.DataResponseParameter;
import com.example.crudspringboot.base.response.ListResponseParameter;
import com.example.crudspringboot.base.response.ResponseHelper;
import com.example.crudspringboot.controller.advices.BaseControllerImpl;
import com.example.crudspringboot.controller.v1.FarmerControllerV1;
import com.example.crudspringboot.request.v1.FarmerRequestV1;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.FarmerResponseV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.services.v1.FarmerServiceV1;
import com.example.crudspringboot.services.v1.MitraServiceV1;
import lombok.RequiredArgsConstructor;

@BaseControllerImpl
@RequiredArgsConstructor
public class FarmerControllerImplV1 implements FarmerControllerV1 {
    private final FarmerServiceV1 farmerServiceV1;

    @Override
    public ListResponseParameter<FarmerResponseV1> index() {
        return ResponseHelper.createResponse(farmerServiceV1.index());
    }

    @Override
    public DataResponseParameter<FarmerResponseV1> store(FarmerRequestV1 request) {
        return ResponseHelper.createResponse(farmerServiceV1.store(request));
    }

    @Override
    public DataResponseParameter<FarmerResponseV1> show(String id) {
        return ResponseHelper.createResponse(farmerServiceV1.show(id));
    }

    @Override
    public DataResponseParameter<FarmerResponseV1> update(String id, FarmerRequestV1 request) {
        return ResponseHelper.createResponse(farmerServiceV1.update(id, request));
    }

    @Override
    public DataResponseParameter<FarmerResponseV1> delete(String id) {
        return ResponseHelper.createResponse(farmerServiceV1.delete(id));
    }
}
