package com.socialapp.backend.post;

import com.socialapp.backend.user.UserDTO;

import java.util.List;

public interface PostService {
    void insertPost(PostDTO postDTO);

    void updatePost(Long id, PostDTO postDTO);

    void deletePostById(Long id);

    PostDTO findPostById(Long id);

    List<UserDTO> findLikedUsers(Long id);
}
