package com.socialapp.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private long id;

    @NotEmpty(message = "username can not be empty")
    private String username;

    @NotEmpty(message = "email can not be empty")
    @Email(message = "Invalid email")
    private String email;

}
