package com.socialapp.backend.post.service;

import com.socialapp.backend.post.dto.PostDTO;
import com.socialapp.backend.post.entity.Post;

public interface PostService {
    PostDTO insertPost(PostDTO postDTO);

    PostDTO updatePost(Long id, PostDTO postDTO);

    void deletePostById(Long id);

    PostDTO findPostById(Long id);
}
