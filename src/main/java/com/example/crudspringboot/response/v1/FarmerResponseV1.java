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

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FarmerResponseV1 {
    private String id;
    private String name;
    private String address;

    private MitraSimpleResponse mitra;
    // Output yang dikeluarkan :
    /*
         "mitra": {
            "id": "101",
            "name": "PT ABC"
         }
     */

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
    public static class MitraSimpleResponse {
        private String id;
        private String name;
    }

}
