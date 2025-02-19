package com.example.crudspringboot.services.v1;

import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface MitraServiceV1 {

    List<MitraResponseV1> index();
    MitraResponseV1 store(MitraRequestV1 req);
    MitraResponseV1 show(String id);
    MitraResponseV1 update(String id, MitraRequestV1 req);
    MitraResponseV1 delete(String id);

    Slice<MitraResponseV1> getMitraActive(Pageable pageable);
    Slice<MitraResponseV1> getMitraInActive(Pageable pageable);
}
