package com.example.crudspringboot.base.exceptions;

import com.example.crudspringboot.base.constant.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
  public BadRequestException(String msg) {
    super(msg);
  }

  public BadRequestException(ErrorCodeEnum errorCodeEnum){
    super(errorCodeEnum.toString());
  }

}
