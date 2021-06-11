package com.socialapp.backend.post.entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String desc;

    private String img;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private User user;
//
//    @ManyToMany(mappedBy = "likedPosts")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonIgnore
//    private Set<User> likedUsers;
}