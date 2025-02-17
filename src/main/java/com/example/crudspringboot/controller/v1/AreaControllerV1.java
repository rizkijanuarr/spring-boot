package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.base.response.DataResponseParameter;
import com.example.crudspringboot.base.response.ListResponseParameter;
import com.example.crudspringboot.controller.advices.BaseController;
import com.example.crudspringboot.request.v1.AreaRequestV1;
import com.example.crudspringboot.response.v1.AreaResponseV1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BaseController("v1/area")
public interface AreaControllerV1 {

    @GetMapping
    ListResponseParameter<AreaResponseV1> index();
    @PostMapping
    DataResponseParameter<AreaResponseV1> store(
            @RequestBody AreaRequestV1 request
    );

}
