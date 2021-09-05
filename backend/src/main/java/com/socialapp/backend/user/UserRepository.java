package com.socialapp.backend.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    int insert(User user);

    int update(User user);

    int deleteById(Long id);

    List<User> findFollowings(Long id);

}