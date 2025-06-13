package com.hotelms.backend.usermodule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String mobile;
    private String role;
    private String password;
}
