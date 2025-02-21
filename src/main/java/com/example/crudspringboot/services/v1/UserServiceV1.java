package com.example.crudspringboot.services.v1;


import com.example.crudspringboot.response.v1.UserResponseV1;

import java.util.List;

public interface UserServiceV1 {

    List<UserResponseV1> index();
}
