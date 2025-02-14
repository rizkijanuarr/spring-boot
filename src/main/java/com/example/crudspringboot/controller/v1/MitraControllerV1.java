package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.controller.advices.BaseController;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.utils.ApiResponse;
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
    ApiResponse<List<MitraResponseV1>> getAll();

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
