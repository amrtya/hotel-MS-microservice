package com.hotelms.backend.usermodule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

    private String responseType;
    private String responseMessage;

    public ResponseDTO(String responseType, String responseMessage) {
        this.responseType = responseType;
        this.responseMessage = responseMessage;
    }
}
