package com.example.crudspringboot.request.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaRequestV1 {
    private String area_name;
    private BigDecimal area_land;
    private String farmer_id;
}
