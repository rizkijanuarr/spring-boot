package com.example.crudspringboot.controller.v1;

import com.example.crudspringboot.annotations.swagger.*;
import com.example.crudspringboot.controller.advices.BaseController;
import com.example.crudspringboot.response.base.BaseResponse;
import com.example.crudspringboot.response.base.BaseResponseSlice;
import com.example.crudspringboot.request.v1.FarmerRequestV1;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@BaseController("v1/farmer")
public interface FarmerControllerV1 {

    @GetEndpoint(
            value = "/",
            tagName = "Farmer Management",
            description = "Get list of all farmers",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> getListFarmer();

    @PostEndpoint(
            value = "/",
            tagName = "Farmer Management",
            description = "Create a new farmer",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> createFarmer(@RequestBody FarmerRequestV1 req);

    @GetEndpoint(
            value = "/{id}",
            tagName = "Farmer Management",
            description = "Details Farmer",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> detailFarmer(@PathVariable("id") String id);

    @PutEndpoint(
            value = "/{id}",
            tagName = "Farmer Management",
            description = "Edit Farmer",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> updateFarmer(@PathVariable("id") String id, @RequestBody FarmerRequestV1 req);

    @DeleteEndpoint(
            value = "/{id}",
            tagName = "Farmer Management",
            description = "Delete Farmer",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponse> deleteFarmer(@PathVariable("id") String id);

    @GetEndpoint(
            value = "/list/ACTIVE",
            tagName = "Farmer Management",
            description = "List Farmer Active",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getFarmerActive(Pageable pageable);

    @GetEndpoint(
            value = "/list/INACTIVE",
            tagName = "Farmer Management",
            description = "List Farmer InActive",
            group = SwaggerTypeGroup.APPS_WEB
    )
    ResponseEntity<BaseResponseSlice> getFarmerInActive(Pageable pageable);

}
