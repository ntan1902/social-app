package com.socialapp.backend.user.service;

import com.socialapp.backend.authen.RegisterRequest;
import com.socialapp.backend.user.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User insertUser(User user);

    User updateUser(User user);

    boolean deleteUser(Long id);

    User findUserById(Long id);

    boolean followUser(Long id, Long userId);

    boolean unfollowUser(Long id, Long userId);
}
