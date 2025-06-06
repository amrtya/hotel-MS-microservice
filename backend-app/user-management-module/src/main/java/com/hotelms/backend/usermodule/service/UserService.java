package com.hotelms.backend.usermodule.service;

import com.hotelms.backend.usermodule.exception.BusinessException;
import com.hotelms.backend.usermodule.model.UserModel;
import com.hotelms.backend.usermodule.model.UserPrincipal;
import com.hotelms.backend.usermodule.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserModel> user = authRepository.findByEmail(email);
        if(user.isPresent()) {
            return new UserPrincipal(user.get());
        }
        throw new BusinessException("User not found");
    }
}
