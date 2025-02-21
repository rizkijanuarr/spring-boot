package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.base.response.BaseResponse;
import com.example.crudspringboot.base.response.BaseResponseSlice;
import com.example.crudspringboot.request.v1.FarmerRequestV1;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/farmer")
public interface FarmerControllerV1 {

    @GetMapping
    ResponseEntity<BaseResponse> index();

    @PostMapping
    ResponseEntity<BaseResponse> store(@RequestBody FarmerRequestV1 req);

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse> show(@PathVariable("id") String id);

    @PutMapping("/{id}")
    ResponseEntity<BaseResponse> update(@PathVariable("id") String id, @RequestBody FarmerRequestV1 req);

    @DeleteMapping("/{id}")
    ResponseEntity<BaseResponse> delete(@PathVariable("id") String id);

    @GetMapping("list/ACTIVE")
    ResponseEntity<BaseResponseSlice> getFarmerActive(Pageable pageable);

    @GetMapping("list/INACTIVE")
    ResponseEntity<BaseResponseSlice> getFarmerInActive(Pageable pageable);

}
