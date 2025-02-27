package com.example.crudspringboot.maxxitaniapps.services.v1;

import com.example.crudspringboot.maxxitaniapps.request.v1.FarmerRequestV1;
import com.example.crudspringboot.maxxitaniapps.response.v1.FarmerResponseV1;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface FarmerServiceV1 {

    List<FarmerResponseV1> getListFarmer();
    FarmerResponseV1 createFarmer(FarmerRequestV1 req);
    FarmerResponseV1 detailFarmer(String id);
    FarmerResponseV1 updateFarmer(String id, FarmerRequestV1 req);
    FarmerResponseV1 deleteFarmer(String id);

    Slice<FarmerResponseV1> getFarmerActive(Pageable pageable);
    Slice<FarmerResponseV1> getFarmerInActive(Pageable pageable);

}
