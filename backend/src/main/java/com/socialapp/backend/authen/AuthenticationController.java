package com.socialapp.backend.authen;

import com.socialapp.backend.jwt.JwtTokenProvider;
import com.socialapp.backend.user.dto.CustomUserDetails;
import com.socialapp.backend.user.dto.User;
import com.socialapp.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        User newUser = User.builder()
                .username(registerRequest.getUsername())
                .password(
                        registerRequest.getPassword()
                )
                .email(registerRequest.getEmail())
                .build();
        User res = this.userService.insertUser(newUser);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/hehe")
    public String getLogin() {
        return "Hehe";
    }
}
