package com.example.crudspringboot.maxxitaniapps.response.v1;

import com.example.crudspringboot.core.utils.enums.MitraTypeEnum;
import com.example.crudspringboot.core.utils.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MitraResponseV1 {
    private String id;
    private String mitra_code;
    private String mitra_name;
    private String mitra_phone;
    private String mitra_address;
    private MitraTypeEnum mitra_type;

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

}