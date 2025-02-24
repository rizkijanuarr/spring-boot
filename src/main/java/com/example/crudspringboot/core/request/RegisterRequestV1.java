package com.example.crudspringboot.core.request;

import com.example.crudspringboot.core.utils.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestV1 {
    private String user_name;
    private String user_email;
    private String user_password;
    private String user_phone;
    private RoleEnum role;
}
