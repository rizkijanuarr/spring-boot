package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.controller.advices.BaseController;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.base.response.DataResponseParameter;
import com.example.crudspringboot.base.response.ListResponseParameter;
import com.example.crudspringboot.base.response.SliceResponseParameter;
import org.springframework.web.bind.annotation.*;

@BaseController("v1/mitra")
public interface MitraControllerV1 {
    @PostMapping("/create")
    DataResponseParameter<MitraResponseV1> create(
            @RequestBody MitraRequestV1 request
    );

    @GetMapping("/{id}")
    DataResponseParameter<MitraResponseV1> getById(
            @PathVariable("id") String id
    );

    @GetMapping("/list")
    ListResponseParameter<MitraResponseV1> getAllList();

    @GetMapping
    SliceResponseParameter<MitraResponseV1> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    );

    @PutMapping("/{id}")
    DataResponseParameter<MitraResponseV1> update(
            @PathVariable("id") String id,
            @RequestBody MitraRequestV1 request
    );

    @DeleteMapping("/{id}")
    DataResponseParameter<MitraResponseV1> delete(
            @PathVariable("id") String id
    );
}
