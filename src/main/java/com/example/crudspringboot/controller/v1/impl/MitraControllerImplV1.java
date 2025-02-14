package com.example.crudspringboot.controller.v1.impl;

import com.example.crudspringboot.controller.advices.BaseControllerImpl;
import com.example.crudspringboot.controller.v1.MitraControllerV1;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import com.example.crudspringboot.response.v1.MitraResponseV1;
import com.example.crudspringboot.services.v1.MitraServiceV1;
import com.example.crudspringboot.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseControllerImpl
@RequiredArgsConstructor
public class MitraControllerImplV1 implements MitraControllerV1 {
    private final MitraServiceV1 mitraService;

    @Override
    public ApiResponse<MitraResponseV1> create(MitraRequestV1 request) {
        MitraResponseV1 responses = mitraService.create(request);
        if (responses != null) {
            return new ApiResponse<>(true, "Created", responses);
        } else {
            return new ApiResponse<>(false, "Failed to create", null);
        }
    }

    @Override
    public ApiResponse<MitraResponseV1> getById(String id) {
        MitraResponseV1 responses = mitraService.getById(id);
        if (responses != null) {
            return new ApiResponse<>(true, "Success", responses);
        } else {
            return new ApiResponse<>(false, "Failed to get", null);
        }
    }

    @Override
    public ApiResponse<List<MitraResponseV1>> getAll() {
        List<MitraResponseV1> responses = mitraService.getAll();
        if (responses != null) {
            return new ApiResponse<>(true, "Success", responses);
        } else {
            return new ApiResponse<>(false, "Failed to get", null);
        }
    }

    @Override
    public ApiResponse<MitraResponseV1> update(String id, MitraRequestV1 request) {
        MitraResponseV1 responses = mitraService.update(id, request);
        if (responses != null) {
            return new ApiResponse<>(true, "Success", responses);
        } else {
            return new ApiResponse<>(false, "Failed to update", null);
        }
    }

    @Override
    public ApiResponse<Void> delete(String id) {
        mitraService.delete(id);
        return new ApiResponse<>(true, "Success", null);
    }
}