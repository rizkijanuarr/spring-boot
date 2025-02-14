package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.controller.advices.BaseController;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.utils.ApiResponse;
import com.example.crudspringboot.utils.keputran.SliceResponseParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseController("v1/mitra")
public interface MitraControllerV1 {
    @PostMapping("/create")
    ApiResponse<MitraResponseV1> create(
            @RequestBody MitraRequestV1 request
    );

    @GetMapping("/{id}")
    ApiResponse<MitraResponseV1> getById(
            @PathVariable("id") String id
    );

    @GetMapping
    SliceResponseParameter<MitraResponseV1> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    );

    @PutMapping("/{id}")
    ApiResponse<MitraResponseV1> update(
            @PathVariable("id") String id,
            @RequestBody MitraRequestV1 request
    );

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(
            @PathVariable("id") String id
    );
}
