package com.example.crudspringboot.utils.keputran;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SliceResponseParameter<T> {
    @Builder.Default
    private Boolean success = true;
    private String message;
    private List<T> data;
    private Boolean isFirst;
    private Boolean isLast;
    private Boolean hasNext;
    private List<ErrorResponse> errors;
    private Long totalData;
    private Integer currentPage;
}
