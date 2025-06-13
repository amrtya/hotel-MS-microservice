package com.hotelms.backend.usermodule.controller;

import com.hotelms.backend.usermodule.dto.*;
import com.hotelms.backend.usermodule.model.UserModel;
import com.hotelms.backend.usermodule.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseDTO signupUser(@RequestBody RegistrationDTO request) {
        return authService.signupUser(request);
    }

    @PostMapping("/login")
    public ResponseDTO loginUser(@RequestBody LoginDTO loginReq) {
        return authService.loginUser(loginReq);
    }

    @GetMapping("/getuserdetails")
    public SingleResponseDTO<UserModel> getUserByMobileNumber(@RequestParam("mobile") String mobileNumber) {
        return authService.getUserDetails(mobileNumber);
    }

    @PostMapping("/updateuserdetails")
    public ResponseDTO updateUserDetails(@RequestBody UpdateUserDTO request) {
        return authService.updateUserDetails(request);
    }
}
