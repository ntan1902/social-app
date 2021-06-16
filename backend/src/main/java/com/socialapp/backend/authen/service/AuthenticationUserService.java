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
    public UserDetails loadUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiResponseException("User " + username + " is not found"));
        return new CustomUserDetails(user);

    }

    public UserDetails loadUserById(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ApiResponseException("User id " + userId + " is not found"));
        return new CustomUserDetails(user);
    }
    // ---- UserDetailsService ----
}
