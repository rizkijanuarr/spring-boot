package com.example.crudspringboot.core.response.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseV1 {
    private String user_name;
    private String user_email;
    private String token;
}
