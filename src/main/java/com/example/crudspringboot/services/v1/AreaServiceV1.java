package com.example.crudspringboot.services.v1;

import com.example.crudspringboot.request.v1.AreaRequestV1;
import com.example.crudspringboot.response.v1.AreaResponseV1;

import java.util.List;

public interface AreaServiceV1 {

    List<AreaResponseV1> index();
    AreaResponseV1 store(AreaRequestV1 req);
    AreaResponseV1 show(String id);
    AreaResponseV1 update(String id, AreaRequestV1 req);
    AreaResponseV1 delete(String id);
}
