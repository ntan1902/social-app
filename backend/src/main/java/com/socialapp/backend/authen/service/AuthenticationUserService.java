package com.socialapp.backend.authen.service;

import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.entity.CustomUserDetails;
import com.socialapp.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class AuthenticationUserService implements UserDetailsService {
    private final UserRepository userRepository;

    // ---- UserDetailsService ----
    @Override
    public UserDetails loadUserByUsername(String username){
        try {
            User user = this.userRepository.findByUsername(username).get();
            return new CustomUserDetails(user);
        } catch (Exception e) {
            log.error("Username " + username + " is not found");
            throw new ApiResponseException("Username " + username + " is not found");
        }

    }

    public UserDetails loadUserById(Long userId) {
        try {
            User user = this.userRepository.findById(userId).get();
            return new CustomUserDetails(user);
        } catch (Exception e) {
            log.error("User id " + userId + " is not found");
            throw new ApiResponseException("User id " + userId + " is not found");
        }
    }
    // ---- UserDetailsService ----
}
