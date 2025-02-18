package com.example.crudspringboot.request.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmerRequestV1 {
    private String farmer_code;
    private String farmer_name;
    private String farmer_phone;
    private String farmer_address;
    private String mitra_id;
}
