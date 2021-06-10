package com.socialapp.backend.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialapp.backend.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="post")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String desc;

    @Column(name = "img")
    private String img;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToMany(mappedBy = "likedPosts")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<User> likedUsers;
}