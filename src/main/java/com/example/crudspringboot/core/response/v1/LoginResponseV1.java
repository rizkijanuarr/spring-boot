package com.example.crudspringboot.core.response.v1;

import com.example.crudspringboot.core.utils.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseV1 {
    private String id;
    private String user_name;
    private String user_email;
    private String phone;
    private RoleEnum role;
    private String token;
}
