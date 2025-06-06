package com.hotelms.backend.usermodule.dto;

import com.hotelms.backend.usermodule.model.UserModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String mobile;
    private String role;
    private String password;

    public UserModel getUserModel(String firstName, String lastName, String address, String email, String mobile, String role, String password) {
        return new UserModel(firstName, lastName, address, email, mobile, role, password);
    }
}
