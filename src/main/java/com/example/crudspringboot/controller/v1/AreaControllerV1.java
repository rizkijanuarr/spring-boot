package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.base.response.DataResponseParameter;
import com.example.crudspringboot.base.response.ListResponseParameter;
import com.example.crudspringboot.base.response.SliceResponseParameter;
import com.example.crudspringboot.controller.advices.BaseController;
import com.example.crudspringboot.request.v1.AreaRequestV1;
import com.example.crudspringboot.response.v1.AreaResponseV1;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@BaseController("v1/area")
public interface AreaControllerV1 {
    @GetMapping
    ListResponseParameter<AreaResponseV1> index();
    @PostMapping
    DataResponseParameter<AreaResponseV1> store(
            @RequestBody AreaRequestV1 req);
    @GetMapping("/{id}")
    DataResponseParameter<AreaResponseV1> show(
            @PathVariable("id") String id);
    @PutMapping("/{id}")
    DataResponseParameter<AreaResponseV1> update(
            @PathVariable("id") String id,
            @RequestBody AreaRequestV1 req);
    @DeleteMapping("/{id}")
    DataResponseParameter<AreaResponseV1> delete(
            @PathVariable("id") String id);

    @GetMapping("list/ACTIVE")
    SliceResponseParameter<AreaResponseV1> getAreaActive(
            Pageable pageable
    );

    @GetMapping("list/INACTIVE")
    SliceResponseParameter<AreaResponseV1> getAreaInActive(
            Pageable pageable
    );

}
