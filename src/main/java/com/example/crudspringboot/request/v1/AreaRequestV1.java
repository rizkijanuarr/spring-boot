package com.example.crudspringboot.request.v1;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AreaRequestV1 {
    private String area_name;
    private BigDecimal area_land;
    private String farmer_id;
    private List<CoordinatesReq> coordinates;

    @Data
    @Builder
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class CoordinatesReq {
        private Integer seq;
        private Double lat;
        private Double lng;
    }
}
