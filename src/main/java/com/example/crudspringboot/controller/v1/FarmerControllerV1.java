package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.base.response.DataResponseParameter;
import com.example.crudspringboot.base.response.ListResponseParameter;
import com.example.crudspringboot.controller.advices.BaseController;
import com.example.crudspringboot.request.v1.FarmerRequestV1;
import com.example.crudspringboot.response.v1.FarmerResponseV1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BaseController("v1/farmer")
public interface FarmerControllerV1 {

    @GetMapping
    ListResponseParameter<FarmerResponseV1> index();
    @PostMapping
    DataResponseParameter<FarmerResponseV1> store(
            @RequestBody FarmerRequestV1 request
    );

}
