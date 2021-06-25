package com.socialapp.backend.authen.service;

import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.user.repository.UserRepository;
import com.socialapp.backend.authen.entity.CustomUserDetails;
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
    public UserDetails loadUserByUsername(String email) {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiResponseException("User " + email + " is not found"));
        return new CustomUserDetails(user);

    }

    // Used by JwtAuthenticationFilter.class
    public UserDetails loadUserById(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ApiResponseException("User id " + userId + " is not found"));
        return new CustomUserDetails(user);
    }
    // ---- UserDetailsService ----
}
