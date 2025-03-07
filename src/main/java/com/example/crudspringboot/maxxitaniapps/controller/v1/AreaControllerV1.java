package com.example.crudspringboot.maxxitaniapps.controller.v1;

import com.example.crudspringboot.core.response.base.BaseResponse;
import com.example.crudspringboot.core.response.base.BaseResponseSlice;
import com.example.crudspringboot.maxxitaniapps.request.v1.AreaRequestV1;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/area")
public interface AreaControllerV1 {

    @GetMapping
    @PreAuthorize("hasRole('SUPER_FO')")
    ResponseEntity<BaseResponse> getListArea();

    @PostMapping
    @PreAuthorize("hasRole('SUPER_FO')")
    ResponseEntity<BaseResponse> createArea(@RequestBody AreaRequestV1 req);

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse> detailArea(@PathVariable("id") String id);

    @PutMapping("/{id}")
    ResponseEntity<BaseResponse> updateArea(@PathVariable("id") String id, @RequestBody AreaRequestV1 req);

    @DeleteMapping("/{id}")
    ResponseEntity<BaseResponse> deleteArea(@PathVariable("id") String id);

    @GetMapping("list/ACTIVE")
    ResponseEntity<BaseResponseSlice> getAreaActive(Pageable pageable);

    @GetMapping("list/INACTIVE")
    ResponseEntity<BaseResponseSlice> getAreaInActive(Pageable pageable);

}
