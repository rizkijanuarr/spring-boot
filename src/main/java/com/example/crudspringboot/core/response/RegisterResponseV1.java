package com.example.crudspringboot.core.response;

import com.example.crudspringboot.core.utils.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponseV1 {
    private String id;
    private String user_name;
    private String user_email;
    private String phone;
    private RoleEnum role;
    private String token;
}