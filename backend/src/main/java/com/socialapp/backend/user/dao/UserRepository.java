package com.socialapp.backend.user.dao;

import com.socialapp.backend.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> insert(User user);

    Optional<User> update(User user);

    void deleteById(Long id);

    List<User> findFollowings(Long id);

    // Test SQL Injection
    Optional<User> findUserUncheckInjection(String username, String password);
    Optional<User> findByUsernameUncheckInjection(String username);
}