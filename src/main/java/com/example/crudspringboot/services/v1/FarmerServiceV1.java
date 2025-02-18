package com.example.crudspringboot.services.v1;

import com.example.crudspringboot.request.v1.FarmerRequestV1;
import com.example.crudspringboot.response.v1.FarmerResponseV1;

import java.util.List;

public interface FarmerServiceV1 {

    List<FarmerResponseV1> index();
    FarmerResponseV1 store(FarmerRequestV1 req);
    FarmerResponseV1 show(String id);
    FarmerResponseV1 update(String id, FarmerRequestV1 req);
    FarmerResponseV1 delete(String id);

}
