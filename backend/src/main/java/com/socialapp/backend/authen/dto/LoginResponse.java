package com.socialapp.backend.authen.dto;

import com.socialapp.backend.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private UserDTO user;
    private String accessToken;
    private String tokenType;
}
