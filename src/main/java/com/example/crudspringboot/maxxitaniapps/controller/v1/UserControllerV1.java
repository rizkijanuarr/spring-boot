package com.example.crudspringboot.maxxitaniapps.controller.v1;

import com.example.crudspringboot.core.response.base.BaseResponse;
import com.example.crudspringboot.core.response.base.BaseResponseSlice;
import com.example.crudspringboot.maxxitaniapps.request.v1.UserRequestV1;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/user")
public interface UserControllerV1 {

    @GetMapping
    ResponseEntity<BaseResponse> getListUser();

    @PostMapping
    ResponseEntity<BaseResponse> createUser(@Valid @RequestBody UserRequestV1 req);

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse> detailUser(@PathVariable("id") String id);

    @PutMapping("/{id}")
    ResponseEntity<BaseResponse> updateUser(@PathVariable("id") String id, @RequestBody UserRequestV1 req);

    @DeleteMapping("/{id}")
    ResponseEntity<BaseResponse> deleteUser(@PathVariable("id") String id);

    @GetMapping("list/ACTIVE")
    ResponseEntity<BaseResponseSlice> getUsersActive(Pageable pageable);

    @GetMapping("list/INACTIVE")
    ResponseEntity<BaseResponseSlice> getUsersInActive(Pageable pageable);

}
