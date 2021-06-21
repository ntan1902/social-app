package com.socialapp.backend.follow.service;

public interface FollowService {
    void insertFollow(Long id, Long followingId);

    void removeFollow(Long id, Long followingId);
}
