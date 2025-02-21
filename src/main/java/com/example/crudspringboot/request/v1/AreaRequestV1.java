package com.example.crudspringboot.request.v1;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

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
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class CoordinatesReq {
        private Integer seq;
        private Double lat;
        private Double lng;
    }
}
