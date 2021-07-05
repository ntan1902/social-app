package com.socialapp.backend.authen.service;

import com.socialapp.backend.authen.dto.LoginRequest;
import com.socialapp.backend.authen.dto.LoginResponse;
import com.socialapp.backend.authen.dto.RegisterRequest;
import com.socialapp.backend.authen.dto.TokenRefreshResponse;
import com.socialapp.backend.util.JwtUtil;
import com.socialapp.backend.authen.mapper.RegisterMapper;
import com.socialapp.backend.user.entity.UserRole;
import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.refresh_token.entity.RefreshToken;
import com.socialapp.backend.refresh_token.service.RefreshTokenService;
import com.socialapp.backend.user.dto.UserDTO;
import com.socialapp.backend.user.mapper.UserMapper;
import com.socialapp.backend.user.repository.UserRepository;
import com.socialapp.backend.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class AuthenticationUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RegisterMapper registerMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    // ---- UserDetailsService ----
    @Override
    public UserDetails loadUserByUsername(String email) {
        log.info("Inside loadUserByUsername of AuthenticationUserService");

        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiResponseException("User " + email + " is not found"));

    }
    // ---- UserDetailsService ----

    // Used by JwtAuthenticationFilter.class
    public User loadUserById(Long userId) {
        log.info("Inside loadUserById of AuthenticationUserService");

        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ApiResponseException("User id " + userId + " is not found"));
    }


    public LoginResponse login(LoginRequest loginRequest) {
        log.info("Inside login of AuthenticationUserService");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user =  (User) authentication.getPrincipal();

        String jwt = jwtUtil.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.insert(user.getId());

        UserDTO userDTO = this.userMapper.map(user);

        return new LoginResponse(userDTO, jwt, refreshToken.getToken(), "Bearer");
    }

    public UserDTO register(RegisterRequest registerRequest) {
        log.info("Inside register of AuthenticationUserService");

        User user = registerMapper.map(registerRequest);
        user.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );
        user.setUserRole(UserRole.USER);

        this.userRepository.insert(user)
                .orElseThrow(() -> new ApiResponseException("Can't insert user"));

        return this.userMapper.map(user);
    }

    public TokenRefreshResponse refreshToken(String token) {
        log.info("Inside refreshToken of AuthenticationUserService");

        RefreshToken refreshToken = refreshTokenService.findByToken(token);

        refreshTokenService.validateToken(refreshToken);

        String jwt = jwtUtil.generateToken(
               this.loadUserById(refreshToken.getUserId())
        );

        return new TokenRefreshResponse(jwt, token, "Bearer");
    }
}
