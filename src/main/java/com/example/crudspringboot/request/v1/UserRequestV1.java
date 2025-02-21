package com.example.crudspringboot.request.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestV1 {
    private String user_name;
    private String user_email;
    private String user_password;
    private String user_phone;
    private String mitra_id;
}

