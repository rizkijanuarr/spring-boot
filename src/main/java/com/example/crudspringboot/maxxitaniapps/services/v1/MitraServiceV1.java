package com.example.crudspringboot.maxxitaniapps.services.v1;

import com.example.crudspringboot.maxxitaniapps.request.v1.MitraRequestV1;
import com.example.crudspringboot.maxxitaniapps.response.v1.MitraResponseV1;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface MitraServiceV1 {

    List<MitraResponseV1> getListMitra();
    MitraResponseV1 createMitra(MitraRequestV1 req);
    MitraResponseV1 detailMitra(String id, String requester);
    MitraResponseV1 updateMitra(String id, MitraRequestV1 req);
    MitraResponseV1 deleteMitra(String id);

    Slice<MitraResponseV1> getMitraActive(Pageable pageable);
    Slice<MitraResponseV1> getMitraInActive(Pageable pageable);
}
