package com.socialapp.backend.authen.controller;

import com.socialapp.backend.authen.dto.LoginRequest;
import com.socialapp.backend.authen.dto.LoginResponse;
import com.socialapp.backend.authen.dto.RegisterRequest;
import com.socialapp.backend.jwt.JwtTokenProvider;
import com.socialapp.backend.user.dto.UserDTO;
import com.socialapp.backend.user.entity.CustomUserDetails;
import com.socialapp.backend.user.mapper.UserMapper;
import com.socialapp.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new ResponseEntity<>(new LoginResponse(jwt), HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {


        UserDTO res = this.userService.insertUser(registerRequest);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
