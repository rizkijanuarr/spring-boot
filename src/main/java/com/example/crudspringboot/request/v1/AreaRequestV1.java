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
    private String area_name; // table utama
    private BigDecimal area_land; // table utama
    private String farmer_id; // table lain
    private List<CoordinatesReq> coordinates; // beda table tapi coordinates CRUD nya gak melalui sana akan tetapi melalui disini ketika Create AREA

    @Data
    @Builder
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class CoordinatesReq {
        private Integer seq;
        private Double lat;
        private Double lng;
    }
}
