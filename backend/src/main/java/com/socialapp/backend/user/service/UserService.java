package com.socialapp.backend.user.service;


import com.socialapp.backend.authen.dto.RegisterRequest;
import com.socialapp.backend.user.dto.UserDTO;

public interface UserService {
    UserDTO insertUser(RegisterRequest registerRequest);

    UserDTO updateUser(Long id, UserDTO user);
//
    void deleteUser(Long id);

    UserDTO findUserById(Long id);

    void followUser(Long id, Long userId);

    void unfollowUser(Long id, Long userId);

    // Test SQL Injection
    boolean loginUncheckInjection(String username, String password);
    boolean loginUncheckInjectionHashPassword(String username, String password);

}
