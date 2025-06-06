package com.hotelms.backend.usermodule.exception;

import com.hotelms.backend.usermodule.dto.ResponseDTO;
import com.hotelms.backend.usermodule.util.ResponseType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleBusinessException(BusinessException bex) {
        return new ResponseDTO(ResponseType.FAILURE.name(), bex.getResponseMessage());
    }

    @ExceptionHandler(TechnicalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDTO handleTechnicalException(TechnicalException tex) {
        return new ResponseDTO(ResponseType.FAILURE.name(), tex.getResponseMessage());
    }
}
