package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.controller.advices.BaseController;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.base.response.DataResponseParameter;
import com.example.crudspringboot.base.response.ListResponseParameter;
import com.example.crudspringboot.base.response.SliceResponseParameter;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@BaseController("v1/mitra")
public interface MitraControllerV1 {

    @GetMapping
    ListResponseParameter<MitraResponseV1> index();
    @PostMapping
    DataResponseParameter<MitraResponseV1> store(
            @RequestBody MitraRequestV1 req);
    @GetMapping("/{id}")
    DataResponseParameter<MitraResponseV1> show(
            @PathVariable("id") String id);
    @PutMapping("/{id}")
    DataResponseParameter<MitraResponseV1> update(
            @PathVariable("id") String id,
            @RequestBody MitraRequestV1 req
    );
    @DeleteMapping("/{id}")
    DataResponseParameter<MitraResponseV1> delete(
            @PathVariable("id") String id
    );

}
