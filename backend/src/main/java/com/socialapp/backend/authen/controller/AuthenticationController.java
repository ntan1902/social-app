package com.socialapp.backend.authen.controller;

import com.socialapp.backend.authen.dto.*;
import com.socialapp.backend.authen.service.AuthenticationUserService;
import com.socialapp.backend.user.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationUserService authenticationUserService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authenticationUserService.login(loginRequest);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        UserDTO res = this.authenticationUserService.register(registerRequest);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        TokenRefreshResponse tokenRefreshResponse = authenticationUserService.refreshToken(refreshToken);

        return ResponseEntity.ok(tokenRefreshResponse);
    }

}
