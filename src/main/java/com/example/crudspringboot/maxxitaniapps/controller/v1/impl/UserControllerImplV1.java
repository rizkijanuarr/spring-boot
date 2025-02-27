package com.example.crudspringboot.maxxitaniapps.controller.v1.impl;

import com.example.crudspringboot.core.response.base.BaseResponse;
import com.example.crudspringboot.core.response.base.BaseResponseSlice;
import com.example.crudspringboot.core.response.base.ResponseHelper;
import com.example.crudspringboot.maxxitaniapps.controller.v1.UserControllerV1;
import com.example.crudspringboot.maxxitaniapps.request.v1.UserRequestV1;
import com.example.crudspringboot.maxxitaniapps.services.v1.UserServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class UserControllerImplV1 implements UserControllerV1 {

    private final UserServiceV1 userService;

    @Override
    public ResponseEntity<BaseResponse> getListUser() {
        return ResponseHelper.buildOkResponse(userService.getListUser());
    }

    @Override
    public ResponseEntity<BaseResponse> createUser(UserRequestV1 req) {
        return ResponseHelper.buildOkResponse(userService.createUser(req));
    }

    @Override
    public ResponseEntity<BaseResponse> detailUser(String id) {
        return ResponseHelper.buildOkResponse(userService.detailUser(id));
    }

    @Override
    public ResponseEntity<BaseResponse> updateUser(String id, UserRequestV1 req) {
        return ResponseHelper.buildOkResponse(userService.updateUser(id, req));
    }

    @Override
    public ResponseEntity<BaseResponse> deleteUser(String id) {
        return ResponseHelper.buildOkResponse(userService.deleteUser(id));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getUsersActive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(userService.getUsersActive(pageable));
    }

    @Override
    public ResponseEntity<BaseResponseSlice> getUsersInActive(Pageable pageable) {
        return ResponseHelper.buildOkeResponse(userService.getUsersInActive(pageable));
    }
}
