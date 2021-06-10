package com.socialapp.backend.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialapp.backend.post.entity.Post;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "cover_picture")
    private String coverPicture;

    @Column(name = "description")
    private String desc;

    @Column(name = "city")
    private String city;

    @Column(name = "from_city")
    private String from;

//    @Column(name = "is_admin")
//    private Boolean isAdmin;

    @Column(name = "relationship")
    @Enumerated(EnumType.ORDINAL)
    private Relationship relationship;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<User> followings;

    @ManyToMany(mappedBy = "followings")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<User> followers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @JoinTable(
            name = "like_post",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<Post> likedPosts;
}
