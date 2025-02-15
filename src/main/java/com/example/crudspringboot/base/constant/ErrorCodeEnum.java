package com.example.crudspringboot.base.constant;

import com.example.crudspringboot.base.message.MessageConfig;
import com.example.crudspringboot.base.response.ErrorResponse;

import java.text.MessageFormat;

public enum ErrorCodeEnum {
    INTERNAL_SERVER_ERROR(5000, "", "internal.server.error"),
    MITRA_NAME_CANNOT_BE_NULL(5000, "", "mitra.name.cannot.be.null"),
    DEFAULT(4000, "", "validate.default");

    private final Integer errorCode;
    private final String title;
    private final String massage;

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getTitle() {
        return title;
    }

    public String getMassage() {
        return massage;
    }

    ErrorCodeEnum(Integer errorCode, String title, String massage) {
        this.errorCode = errorCode;
        this.title = title;
        this.massage = massage;
    }

    public ErrorResponse getErrorResponse(MessageConfig messageConfig) {
        return new ErrorResponse(this.errorCode, messageConfig.get(this.title), messageConfig.get(this.massage));
    }

    public ErrorResponse getErrorResponse(String message, MessageConfig messageConfig) {
        return new ErrorResponse(this.errorCode, messageConfig.get(this.title), message);
    }

    public ErrorResponse getErrorResponse(MessageConfig messageConfig, String message) {
        String massageData = MessageFormat.format(messageConfig.get(this.massage), message);
        return new ErrorResponse(this.errorCode, messageConfig.get(this.title), massageData);
    }

    public static ErrorCodeEnum getErrorCodeEnum(String errorCodeEnum) {
        try {
            return ErrorCodeEnum.valueOf(errorCodeEnum);
        } catch (Exception exception) {
            return DEFAULT;
        }
    }

}