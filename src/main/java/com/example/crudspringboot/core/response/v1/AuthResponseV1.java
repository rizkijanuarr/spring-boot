package com.example.crudspringboot.core.response.v1;

import com.example.crudspringboot.core.utils.date.DateUtil;
import com.example.crudspringboot.core.utils.enums.RoleEnum;
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
public class AuthResponseV1 {
    private String id;
    private String user_name;
    private String user_email;
    private String phone;
    private RoleEnum role;
    private String token;

    private Boolean active;

    @JsonIgnore
    private Date createdDate;
    @JsonIgnore
    private Date modifiedDate;
    @JsonIgnore
    private Date deletedDate;

    private String deletedBy;
    private String modifiedBy;

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
