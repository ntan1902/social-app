package com.socialapp.backend.user.entity;

import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Data
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    @Required
    private String username;

    @Column(name = "password")
    @Required
    private String password;

    @Column(name = "email")
    private String email;

//    @Column(name = "profile_picture")
//    private String profilePicture;
//
//    @Column(name = "cover_picture")
//    private String coverPicture;

//    @ManyToMany
//    private List<User> followings;
}
