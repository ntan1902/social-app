package com.socialapp.backend.user.service;

import com.socialapp.backend.user.dto.User;

public interface UserService {
    User insertUser(User user);

//    User updateUser(Long id, User user);
//
//    void deleteUser(Long id);

    User findUserById(Long id);

//    void followUser(Long id, Long userId);
//
//    void unfollowUser(Long id, Long userId);
}
