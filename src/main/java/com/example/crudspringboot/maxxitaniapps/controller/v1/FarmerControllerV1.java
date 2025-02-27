package com.example.crudspringboot.maxxitaniapps.controller.v1;

import com.example.crudspringboot.core.response.base.BaseResponse;
import com.example.crudspringboot.core.response.base.BaseResponseSlice;
import com.example.crudspringboot.maxxitaniapps.request.v1.FarmerRequestV1;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/farmer")
public interface FarmerControllerV1 {

    @GetMapping
    ResponseEntity<BaseResponse> getListFarmer();

    @PostMapping
    ResponseEntity<BaseResponse> createFarmer(@RequestBody FarmerRequestV1 req);

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse> detailFarmer(@PathVariable("id") String id);

    @PutMapping("/{id}")
    ResponseEntity<BaseResponse> updateFarmer(@PathVariable("id") String id, @RequestBody FarmerRequestV1 req);

    @DeleteMapping("/{id}")
    ResponseEntity<BaseResponse> deleteFarmer(@PathVariable("id") String id);

    @GetMapping("list/ACTIVE")
    ResponseEntity<BaseResponseSlice> getFarmerActive(Pageable pageable);

    @GetMapping("list/INACTIVE")
    ResponseEntity<BaseResponseSlice> getFarmerInActive(Pageable pageable);

}
