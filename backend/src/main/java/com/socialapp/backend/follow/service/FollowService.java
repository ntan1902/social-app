package com.socialapp.backend.follow.service;

import com.socialapp.backend.follow.entity.Follow;

import java.util.List;

public interface FollowService {
    void insert(Follow follow);

    void delete(Long id, Long followingId);

    List<Follow> findFollowings(Long id);
}
