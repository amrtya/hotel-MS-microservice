package com.hotelms.backend.usermodule.service;

import com.hotelms.backend.usermodule.dto.LoginDTO;
import com.hotelms.backend.usermodule.dto.RegistrationDTO;
import com.hotelms.backend.usermodule.dto.ResponseDTO;
import com.hotelms.backend.usermodule.exception.BusinessException;
import com.hotelms.backend.usermodule.model.UserModel;
import com.hotelms.backend.usermodule.repository.AuthRepository;
import com.hotelms.backend.usermodule.util.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
}
