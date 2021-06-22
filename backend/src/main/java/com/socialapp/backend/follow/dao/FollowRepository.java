package com.socialapp.backend.follow.dao;

import com.socialapp.backend.follow.entity.Follow;

import java.util.List;
import java.util.Optional;

public interface FollowRepository {
    void insert(Long id, Long followingId);

    void remove(Long id, Long followingId);

    boolean isUserInFollowings(Long id, Long userId);

    Optional<List<Follow>> findAllByUserId(Long id);
}
