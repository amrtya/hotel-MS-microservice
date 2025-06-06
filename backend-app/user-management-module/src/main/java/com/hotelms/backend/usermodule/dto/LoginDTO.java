package com.hotelms.backend.usermodule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    private String email;
    private String mobile;
    private String password;
}
