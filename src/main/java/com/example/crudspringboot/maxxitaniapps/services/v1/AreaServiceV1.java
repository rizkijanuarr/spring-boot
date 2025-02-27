package com.example.crudspringboot.maxxitaniapps.services.v1;

import com.example.crudspringboot.maxxitaniapps.request.v1.AreaRequestV1;
import com.example.crudspringboot.maxxitaniapps.response.v1.AreaResponseV1;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface AreaServiceV1 {

    List<AreaResponseV1> getListArea();
    AreaResponseV1 createArea(AreaRequestV1 req);
    AreaResponseV1 detailArea(String id);
    AreaResponseV1 updateArea(String id, AreaRequestV1 req);
    AreaResponseV1 deleteArea(String id);

    Slice<AreaResponseV1> getAreaActive(Pageable pageable);
    Slice<AreaResponseV1> getAreaInActive(Pageable pageable);
}
