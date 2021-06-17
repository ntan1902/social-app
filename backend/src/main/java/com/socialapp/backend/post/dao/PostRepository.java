package com.socialapp.backend.post.dao;

import com.socialapp.backend.post.entity.Post;

import java.util.Optional;

public interface PostRepository{
    Optional<Post> insertPost(Post post);

    Optional<Post> updatePost(Post post);

    void deletePostById(Long id);

    Optional<Post> findPostById(Long id);
}
