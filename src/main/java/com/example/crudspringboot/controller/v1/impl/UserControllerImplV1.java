package com.example.crudspringboot.controller.v1.impl;

import com.example.crudspringboot.base.response.BaseResponse;
import com.example.crudspringboot.base.response.ResponseHelper;
import com.example.crudspringboot.controller.v1.UserControllerV1;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.request.v1.UserRequestV1;
import com.example.crudspringboot.services.v1.UserServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class UserControllerImplV1 implements UserControllerV1 {

    private final UserServiceV1 userService;

    @Override
    public ResponseEntity<BaseResponse> index() {
        return ResponseHelper.buildOkResponse(userService.index());
    }

    @Override
    public ResponseEntity<BaseResponse> store(UserRequestV1 req) {
        return ResponseHelper.buildOkResponse(userService.store(req));
    }

    @Override
    public ResponseEntity<BaseResponse> show(String id) {
        return ResponseHelper.buildOkResponse(userService.show(id));
    }

    @Override
    public ResponseEntity<BaseResponse> update(String id, UserRequestV1 req) {
        return ResponseHelper.buildOkResponse(userService.update(id, req));
    }

    @Override
    public ResponseEntity<BaseResponse> delete(String id) {
        return ResponseHelper.buildOkResponse(userService.delete(id));
    }

}
