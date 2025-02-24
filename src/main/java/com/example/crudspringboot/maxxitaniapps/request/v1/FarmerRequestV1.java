package com.example.crudspringboot.maxxitaniapps.request.v1;

import lombok.*;

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
