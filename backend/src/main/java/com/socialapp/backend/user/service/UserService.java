package com.socialapp.backend.user.service;


import com.socialapp.backend.authen.dto.RegisterRequest;
import com.socialapp.backend.post.dto.UserPostDTO;
import com.socialapp.backend.user.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO insertUser(RegisterRequest registerRequest);

    UserDTO updateUser(Long id, UserDTO user);
//
    void deleteUserById(Long id);

    UserDTO findUserById(Long id);

    List<UserPostDTO> findAllPosts(Long id);

    List<UserDTO> findAllFollowings(Long id);

    // Test SQL Injection
    boolean loginUncheckInjection(String username, String password);
    boolean loginUncheckInjectionHashPassword(String username, String password);

}
