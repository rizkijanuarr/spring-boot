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


/*
    -   NOTED :
        - Digunakan ketika response yang dikeluarkan berbeda dengan kolom yang ada di database.
        - Biasanya dibutuhkan saat kontrak API dengan frontend harus mengikuti format tertentu.

        - Contoh penggunaan :
            @JsonProperty("mitra_id")
            private String mitraId;

     - Saat mapping response, hasilnya seperti berikut :

      private AreaResponseV1 responses(AreaEntity entity) {
        return AreaResponseV1.builder()
                .id(entity.getId())
                .area_name(entity.getArea_name())
                .area_land(entity.getArea_land())
                .farmer_id(entity.getFarmer().getId()) // 2 table
                .mitra_id(entity.getFarmer().getMitra().getId()) // 3 table
                .farmer(AreaResponseV1.FarmerResponse.builder()
                        .id(entity.getFarmer().getId())
                        .farmer_code(entity.getFarmer().getFarmer_code())
                        .farmer_name(entity.getFarmer().getFarmer_name())
                        .mitra(AreaResponseV1.MitraResponse.builder()
                                .id(entity.getFarmer().getMitra().getId())
                                .mitra_code(entity.getFarmer().getMitra().getMitra_code())
                                .mitra_name(entity.getFarmer().getMitra().getMitra_name())
                                .build()
                        )
                        .build())
                .coordinates(entity.getCoordinates().stream()
                        .map(this::mapToCoordinates)
                        .collect(Collectors.toList()))
                .active(entity.getActive())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .deletedDate(entity.getDeletedDate())
                .deletedBy(entity.getDeletedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    -   OUTPUT JSON:

    {
        "id": "A001",
        "area_name": "Sawah Subur",
        "area_land": 10.5,
        "farmer_id": "F001",
        "mitra_id": "M001",
        "farmer": {
            "id": "F001",
            "farmer_code": "FARM001",
            "farmer_name": "John Doe",
            "mitra": {
                "id": "M001",
                "mitra_code": "MS123",
                "mitra_name": "Mitra Sejahtera"
            }
        },
        "coordinates": [
            {
                "latitude": -7.250445,
                "longitude": 112.768845
            },
            {
                "latitude": -7.251567,
                "longitude": 112.769956
            }
        ],
        "active": true,
        "created_date": "2024-02-25 10:00:00",
        "modified_date": "2024-02-26 15:30:00",
        "deleted_date": null,
        "deleted_by": null,
        "modified_by": "admin"
    }
 */



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
    private String farmer_id;
    private String mitra_id;
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