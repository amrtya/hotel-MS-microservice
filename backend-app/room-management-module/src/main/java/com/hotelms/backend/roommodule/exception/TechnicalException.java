package com.hotelms.backend.roommodule.exception;

import lombok.Getter;

@Getter
public class TechnicalException extends RuntimeException {

    public final String responseMessage;

    public TechnicalException(String message) {
        super(message);
        this.responseMessage = message;
    }
}
