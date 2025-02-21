package com.example.crudspringboot.request.v1;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FarmerRequestV1 {
    private String farmer_code;
    private String farmer_name;
    private String farmer_phone;
    private String farmer_address;
    private String mitra_id;
}
