package com.socialapp.backend.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    Optional<User> insert(User user);

    Optional<User> update(User user);

    void deleteById(Long id);

    List<User> findFollowings(Long id);

}