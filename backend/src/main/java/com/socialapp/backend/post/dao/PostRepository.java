package com.socialapp.backend.post.dao;

import com.socialapp.backend.post.entity.Post;
import com.socialapp.backend.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository{
    Optional<Post> insertPost(Post post);

    Optional<Post> updatePost(Post post);

    void deletePostById(Long id);

    Optional<Post> findPostById(Long id);

    boolean isUserLikedPost(Long id, Long userId);

    void insertLike(Long id, Long userId);

    void removeLike(Long id, Long userId);

    Optional<List<Post>> findPostsByUserId(Long userId);

    Optional<List<User>> findLikesOfPost(Long id);
}
