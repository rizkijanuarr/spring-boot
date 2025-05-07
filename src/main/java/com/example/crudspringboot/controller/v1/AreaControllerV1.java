package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.annotations.swagger.*;
import com.example.crudspringboot.controller.advices.BaseController;
import com.example.crudspringboot.response.base.BaseResponse;
import com.example.crudspringboot.response.base.BaseResponseSlice;
import com.example.crudspringboot.request.v1.AreaRequestV1;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@BaseController("v1/area")
public interface AreaControllerV1 {

    @GetEndpoint(
            value = "/",
            tagName = "Area Management",
            description = "Get list of all Area",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> getListArea();

    @PostEndpoint(
            value = "/",
            tagName = "Area Management",
            description = "Create a new Area",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> createArea(@RequestBody AreaRequestV1 req);

    @GetEndpoint(
            value = "/{id}",
            tagName = "Area Management",
            description = "Details Area",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> detailArea(@PathVariable("id") String id);

    @PutEndpoint(
            value = "/{id}",
            tagName = "Area Management",
            description = "Edit Area",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> updateArea(@PathVariable("id") String id, @RequestBody AreaRequestV1 req);

    @DeleteEndpoint(
            value = "/{id}",
            tagName = "Area Management",
            description = "Delete Area",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> deleteArea(@PathVariable("id") String id);

    @GetEndpoint(
            value = "/list/ACTIVE",
            tagName = "Area Management",
            description = "List Area Active",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getAreaActive(Pageable pageable);

    @GetEndpoint(
            value = "/list/INACTIVE",
            tagName = "Area Management",
            description = "List Area InActive",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getAreaInActive(Pageable pageable);

}
