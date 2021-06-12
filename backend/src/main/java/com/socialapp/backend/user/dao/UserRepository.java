package com.socialapp.backend.user.dao;

import com.socialapp.backend.user.dto.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> loadUserById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> insertUser(User user);

    Optional<User> updateUser(User user);

    void deleteById(Long id);
}