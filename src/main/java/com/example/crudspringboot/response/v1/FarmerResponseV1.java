package com.example.crudspringboot.response.v1;

import com.example.crudspringboot.utils.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Date;


/*
    -   NOTED :
        - Digunakan ketika response yang dikeluarkan berbeda dengan kolom yang ada di database.
        - Biasanya dibutuhkan saat kontrak API dengan frontend harus mengikuti format tertentu.

        - Contoh penggunaan :
            @JsonProperty("mitra_id")
            private String mitraId;

     - Saat mapping response, hasilnya seperti berikut :

     private FarmerResponseV1 responses(FarmerEntity entity) {
        return FarmerResponseV1.builder()
                .id(entity.getId())
                .farmer_code(entity.getFarmer_code())
                .farmer_name(entity.getFarmer_name())
                .farmer_phone(entity.getFarmer_phone())
                .farmer_address(entity.getFarmer_address())
                .mitra_id(entity.getMitra().getId()) // Ini berupa string
                .mitra(FarmerResponseV1.MitraResponse.builder() // Ini berupa objek
                        .id(entity.getMitra().getId())
                        .mitra_name(entity.getMitra().getMitra_name())
                        .mitra_code(entity.getMitra().getMitra_code())
                        .build())
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
        "id": "123",
        "farmer_code": "F001",
        "farmer_name": "John Doe",
        "farmer_phone": "08123456789",
        "farmer_address": "Jl. Kebun No. 10",
        "mitra_id": "M001", // string
        "mitra": { // object
            "id": "M001",
            "mitra_name": "Mitra Sejahtera",
            "mitra_code": "MS123"
        },
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
public class FarmerResponseV1 {
    private String id;
    private String farmer_code;
    private String farmer_name;
    private String farmer_phone;
    private String farmer_address;
    private MitraResponse mitra;
    private String mitra_id;

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
    public static class MitraResponse {
        private String id;
        private String mitra_name;
        private String mitra_code;
    }

}
