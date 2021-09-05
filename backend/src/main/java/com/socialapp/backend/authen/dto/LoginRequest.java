package com.socialapp.backend.authen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "email can not be empty")
    private String email;

    @NotEmpty(message = "password can not be empty")
    private String password;
}
