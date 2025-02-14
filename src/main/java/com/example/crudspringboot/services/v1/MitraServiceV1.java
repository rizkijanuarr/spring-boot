package com.example.crudspringboot.services.v1;

import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface MitraServiceV1 {
    MitraResponseV1 create(MitraRequestV1 request);
    MitraResponseV1 getById(String id);
    Slice<MitraResponseV1> getAll(Integer page, Integer size);
    MitraResponseV1 update(String id, MitraRequestV1 request);
    void delete(String id);
}
