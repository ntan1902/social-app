package com.socialapp.backend.authen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "username can not be empty")
    private String username;

    @NotEmpty(message = "email can not be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "username can not be empty")
    private String password;
}
