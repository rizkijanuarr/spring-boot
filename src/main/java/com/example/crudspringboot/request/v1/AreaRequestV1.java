package com.example.crudspringboot.request.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaRequestV1 {
    private String name;
    private Integer wide;
    private String mitraId;
    private String farmerId;
}
