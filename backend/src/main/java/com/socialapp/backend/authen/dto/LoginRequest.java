package com.socialapp.backend.authen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "username can not be empty")
    private String username;

    @NotEmpty(message = "password can not be empty")
    private String password;
}
