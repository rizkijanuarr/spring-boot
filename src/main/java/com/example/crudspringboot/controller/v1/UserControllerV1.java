package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.base.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/user")
public interface UserControllerV1 {

    @GetMapping
    ResponseEntity<BaseResponse> index();

}
