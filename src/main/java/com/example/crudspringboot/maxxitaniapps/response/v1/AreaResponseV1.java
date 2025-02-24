package com.example.crudspringboot.maxxitaniapps.response.v1;

import com.example.crudspringboot.core.utils.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AreaResponseV1 {
    private String id;
    private String area_name;
    private BigDecimal area_land;
    private FarmerResponse farmer;
    private List<CoordinatesResponse> coordinates;

    @JsonIgnore
    private Date createdDate;
    @JsonIgnore
    private Date modifiedDate;
    @JsonIgnore
    private Date deletedDate;

    private String deletedBy;
    private String modifiedBy;
    private Boolean active;

    @JsonProperty("created_date")
    public String getCreatedDate() {
        return DateUtil.formatLongDateTime(createdDate);
    }
    @JsonProperty("modified_date")
    public String getModifiedDate() {
        return DateUtil.formatLongDateTime(modifiedDate);
    }
    @JsonProperty("deleted_date")
    public String getDeletedDate() {
        return DateUtil.formatLongDateTime(deletedDate);
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