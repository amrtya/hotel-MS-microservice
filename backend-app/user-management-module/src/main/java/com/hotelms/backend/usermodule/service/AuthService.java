package com.hotelms.backend.usermodule.service;

import com.hotelms.backend.usermodule.dto.*;
import com.hotelms.backend.usermodule.exception.BusinessException;
import com.hotelms.backend.usermodule.model.UserModel;
import com.hotelms.backend.usermodule.repository.AuthRepository;
import com.hotelms.backend.usermodule.util.DateTimeUtil;
import com.hotelms.backend.usermodule.util.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseDTO signupUser(RegistrationDTO newUserObject) {

        Optional<UserModel> custByEmailAndMobileNo =
                authRepository.findByEmailAndMobile(newUserObject.getEmail(), newUserObject.getMobile());

        if(custByEmailAndMobileNo.isPresent()) {
            throw new BusinessException("User with same email and mobile already exists");
        }

        UserModel user = newUserObject.getUserModel(
                newUserObject.getFirstName(),
                newUserObject.getLastName(),
                newUserObject.getAddress(),
                newUserObject.getEmail(),
                newUserObject.getMobile(),
                newUserObject.getRole(),
                encoder.encode(newUserObject.getPassword())
        );

        authRepository.save(user);
        return new ResponseDTO(ResponseType.SUCCESS.name(), "User registered successfully");
    }


    public ResponseDTO loginUser(LoginDTO loginReq) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword())
        );

        if(authentication.isAuthenticated()) {
            return new ResponseDTO(ResponseType.SUCCESS.name(), jwtService.generateToken(loginReq.getEmail()));
        }

        return new ResponseDTO(ResponseType.FAILURE.name(), "Login failed");
    }

    public SingleResponseDTO<UserModel> getUserDetails(String mobileNumber) {
        Optional<UserModel> userByMobile = authRepository.findByMobileNo(mobileNumber);

        if (userByMobile.isEmpty()) {
            throw new BusinessException("User not found by this mobile number");
        }
        return new SingleResponseDTO<UserModel>(ResponseType.SUCCESS.name(),
                "Fetched user details", userByMobile.get()
        );
    }

    public ResponseDTO updateUserDetails(UpdateUserDTO request) {
        Optional<UserModel> userById = authRepository.findById(request.getUserId());

        if(userById.isEmpty())
            throw new BusinessException("User doesn't exist");

        if(!request.getFirstName().isEmpty()) userById.get().setFirstName(request.getFirstName());
        if(!request.getLastName().isEmpty()) userById.get().setLastName(request.getLastName());
        if(!request.getAddress().isEmpty()) userById.get().setAddress(request.getAddress());
        if(!request.getEmail().isEmpty()) userById.get().setEmail(request.getEmail());
        if(!request.getMobile().isEmpty()) userById.get().setMobileNo(request.getMobile());
        if(!request.getRole().isEmpty()) userById.get().setRole(request.getRole());
        if(!request.getPassword().isEmpty()) userById.get().setPassword(encoder.encode(request.getPassword()));

        userById.get().setUpdatedAt(DateTimeUtil.currentDateTime());

        authRepository.save(userById.get());

        return new ResponseDTO(ResponseType.SUCCESS.name(), "Updated user details successfully");
    }
}
