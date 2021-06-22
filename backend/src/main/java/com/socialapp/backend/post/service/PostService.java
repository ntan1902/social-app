package com.socialapp.backend.post.service;

import com.socialapp.backend.post.dto.PostDTO;
import com.socialapp.backend.post.dto.UserPostDTO;
import com.socialapp.backend.post.entity.Post;
import com.socialapp.backend.user.dto.UserDTO;

import java.util.List;

public interface PostService {
    PostDTO insertPost(PostDTO postDTO);

    PostDTO updatePost(Long id, PostDTO postDTO);

    void deletePostById(Long id);

    PostDTO findPostById(Long id);

    List<UserDTO> findLikedUsers(Long id);
}
