package com.example.crudspringboot.response.v1;

import com.example.crudspringboot.repositories.enumaration.MitraTypeEnum;
import com.example.crudspringboot.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MitraResponseV1 {
    private String id;
    private String name;
    private String address;
    private MitraTypeEnum type;
    @JsonIgnore
    private LocalDateTime createdDate;
    public String getDate() {
        return DateUtils.formatShortMonth(createdDate); // "10 Sept 2024"
    }
}