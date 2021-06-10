package com.socialapp.backend.authen;

import com.socialapp.backend.jwt.JwtTokenProvider;
import com.socialapp.backend.user.entity.CustomUserDetails;
import com.socialapp.backend.user.entity.User;
import com.socialapp.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
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
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {

        String hashedPassword = passwordEncoder.encode(
                registerRequest.getPassword()
        );

        User newUser = User.builder()
                .username(registerRequest.getUsername())
                .password(hashedPassword)
                .email(registerRequest.getEmail())
                .build();
        User res = this.userService.insertUser(newUser);

        if (res != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/hehe")
    public String getLogin() {
        return "Hehe";
    }
}
