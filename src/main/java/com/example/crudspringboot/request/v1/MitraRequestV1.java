package com.example.crudspringboot.request.v1;

import com.example.crudspringboot.base.enums.MitraTypeEnum;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MitraRequestV1 {
    private String mitra_code;
    private String mitra_name;
    private String mitra_phone;
    private String mitra_address;
    private MitraTypeEnum mitra_type;
}