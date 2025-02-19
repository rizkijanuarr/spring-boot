package com.example.crudspringboot.request.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaRequestV1 {
    private String area_name;
    private BigDecimal area_land;
    private String farmer_id;
    private List<CoordinatesReq> coordinates;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoordinatesReq {
        private Integer seq;
        private Double lat;
        private Double lng;
    }
}
