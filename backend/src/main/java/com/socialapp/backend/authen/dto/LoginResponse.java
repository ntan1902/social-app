package com.socialapp.backend.authen.dto;

import com.socialapp.backend.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
public class LoginResponse {
    private UserDTO user;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
}
