package com.example.crudspringboot.response.v1;

import com.example.crudspringboot.base.date.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AreaResponseV1 {
    private String id;
    private String area_name;
    private BigDecimal area_land;
    private FarmerResponse farmer;
    private List<CoordinatesResponse> coordinates;

    @JsonIgnore
    private LocalDateTime createdDate;
    @JsonIgnore
    private LocalDateTime modifiedDate;
    @JsonIgnore
    private LocalDateTime deletedDate;

    private String deletedBy;
    private String modifiedBy;
    private Boolean active;

    @JsonProperty("created_date")
    public String getCreatedDate() {
        return Date.formatShortMonth(createdDate); // "10 Sept 2024"
    }
    @JsonProperty("modified_date")
    public String getModifiedDate() {
        return Date.formatShortMonth(modifiedDate); // "10 Sept 2024"
    }
    @JsonProperty("deleted_date")
    public String getDeletedDate() {
        return Date.formatShortMonth(deletedDate); // "10 Sept 2024"
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FarmerResponse {
        private String id;
        private String farmer_code;
        private String farmer_name;
        private MitraResponse mitra;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MitraResponse {
        private String id;
        private String mitra_code;
        private String mitra_name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoordinatesResponse {
        private Integer seq;
        private Double lat;
        private Double lng;
    }
}