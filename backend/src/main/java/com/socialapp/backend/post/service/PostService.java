package com.socialapp.backend.post.service;

import com.socialapp.backend.post.entity.Post;

public interface PostService {
    Post insertPost(Post post);

    Post updatePost(Long id, Post post);
}
