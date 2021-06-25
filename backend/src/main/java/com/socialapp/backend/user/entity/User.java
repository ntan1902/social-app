package com.socialapp.backend.user.entity;

import com.socialapp.backend.authen.role.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private String email;

    private String profilePicture;

    private String coverPicture;

    private String description;

    private String city;

    private String fromCity;

    private Relationship relationship;

    private UserRole userRole;

}
