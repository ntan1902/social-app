package com.socialapp.backend.user.dao;

import com.socialapp.backend.user.dto.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long userId);

    User insertUser(User user);
}