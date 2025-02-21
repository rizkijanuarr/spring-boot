package com.example.crudspringboot.services.v1;


import com.example.crudspringboot.request.v1.UserRequestV1;
import com.example.crudspringboot.response.v1.UserResponseV1;

import java.util.List;

public interface UserServiceV1 {

    List<UserResponseV1> index();
    UserResponseV1 store(UserRequestV1 req);
    UserResponseV1 show(String id);
    UserResponseV1 update(String id, UserRequestV1 req);
    UserResponseV1 delete(String id);
}
