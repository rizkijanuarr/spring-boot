package com.example.crudspringboot.core.request.v1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestV1 {
    private String user_email;
    private String user_password;
}
