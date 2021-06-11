package com.socialapp.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
//
//    private String profilePicture;
//
//    private String coverPicture;
//
//    private String desc;
//
//    private String city;
//
//    private String from;

//    @Column(name = "is_admin")
//    private Boolean isAdmin;


}
