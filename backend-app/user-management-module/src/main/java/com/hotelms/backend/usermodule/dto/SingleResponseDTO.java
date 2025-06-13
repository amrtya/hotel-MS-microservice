package com.hotelms.backend.usermodule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponseDTO<T> extends ResponseDTO {

    private String responseType;
    private String responseMessage;
    private T result;

    public SingleResponseDTO(String responseType, String responseMessage, T result) {
        super(responseType, responseMessage);
        this.responseType = responseType;
        this.responseMessage = responseMessage;
        this.result = result;
    }
}
