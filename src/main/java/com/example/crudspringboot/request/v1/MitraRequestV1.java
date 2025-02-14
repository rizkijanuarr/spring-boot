package com.example.crudspringboot.request.v1;


import com.example.crudspringboot.repositories.enumaration.MitraTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MitraRequestV1 {
    private String name;
    private String address;
    private MitraTypeEnum type;
}