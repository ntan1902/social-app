package com.socialapp.backend.follow.service;

import com.socialapp.backend.follow.entity.Follow;

public interface FollowService {
    void insertFollow(Follow follow);

    void deleteFollow(Long id, Long followingId);
}
