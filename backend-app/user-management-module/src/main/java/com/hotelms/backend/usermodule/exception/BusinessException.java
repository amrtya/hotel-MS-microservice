package com.hotelms.backend.usermodule.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    public final String responseMessage;

    public BusinessException(String message) {
        super(message);
        this.responseMessage = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.responseMessage = message;
    }
}
