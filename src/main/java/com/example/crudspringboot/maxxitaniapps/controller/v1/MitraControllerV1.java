package com.example.crudspringboot.maxxitaniapps.controller.v1;

import com.example.crudspringboot.core.response.base.BaseResponse;
import com.example.crudspringboot.core.response.base.BaseResponseSlice;
import com.example.crudspringboot.maxxitaniapps.request.v1.MitraRequestV1;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping(value = "/v1/mitra")
public interface MitraControllerV1 {

    @GetMapping
    ResponseEntity<BaseResponse> index();

    @PostMapping
    ResponseEntity<BaseResponse> store(@Valid @RequestBody MitraRequestV1 req);

    @GetMapping("/{id}")
    ResponseEntity<BaseResponse> show(@PathVariable("id") String id);

    @PutMapping("/{id}")
    ResponseEntity<BaseResponse> update(@PathVariable("id") String id, @RequestBody MitraRequestV1 req);

    @DeleteMapping("/{id}")
    ResponseEntity<BaseResponse> delete(@PathVariable("id") String id);

    @GetMapping("list/ACTIVE")
    ResponseEntity<BaseResponseSlice> getMitraActive(Pageable pageable);

    @GetMapping("list/INACTIVE")
    ResponseEntity<BaseResponseSlice> getMitraInActive(Pageable pageable);

}
