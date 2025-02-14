package com.example.crudspringboot.services.v1;

import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;

import java.util.List;

public interface MitraServiceV1 {
    MitraResponseV1 create(MitraRequestV1 request);
    MitraResponseV1 getById(String id);
    List<MitraResponseV1> getAll();
    MitraResponseV1 update(String id, MitraRequestV1 request);
    void delete(String id);
}
