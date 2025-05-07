package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.annotations.swagger.*;
import com.example.crudspringboot.controller.advices.BaseController;
import com.example.crudspringboot.response.base.BaseResponse;
import com.example.crudspringboot.response.base.BaseResponseSlice;
import com.example.crudspringboot.request.v1.MitraRequestV1;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@BaseController("v1/mitra")
public interface MitraControllerV1 {

    @GetEndpoint(
            value = "/",
            tagName = "Mitra Management",
            description = "Get list of all Mitras",
            group = SwaggerTypeGroup.APPS_WEB
    )
    @PreAuthorize("hasRole('BASIC_FO')")
    ResponseEntity<BaseResponse> getListMitra();

    @PostEndpoint(
            value = "/",
            tagName = "Mitra Management",
            description = "Create a new Mitra",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> createMitra(@Valid @RequestBody MitraRequestV1 req);

    @GetEndpoint(
            value = "/{id}",
            tagName = "Mitra Management",
            description = "Details Mitra",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> detailMitra(@PathVariable("id") String id);

    @PutEndpoint(
            value = "/{id}",
            tagName = "Mitra Management",
            description = "Edit Mitra",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> updateMitra(@PathVariable("id") String id, @RequestBody MitraRequestV1 req);

    @DeleteEndpoint(
            value = "/{id}",
            tagName = "Mitra Management",
            description = "Delete Mitra",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> deleteMitra(@PathVariable("id") String id);

    @GetEndpoint(
            value = "/list/ACTIVE",
            tagName = "Mitra Management",
            description = "List Mitra Active",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getMitraActive(Pageable pageable);

    @GetEndpoint(
            value = "/list/INACTIVE",
            tagName = "Mitra Management",
            description = "List Mitra InActive",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getMitraInActive(Pageable pageable);

}
