package com.socialapp.backend.follow.dao;

public interface FollowRepository {
    void insertFollow(Long id, Long followingId);

    void removeFollow(Long id, Long followingId);

    boolean isUserInFollowings(Long id, Long userId);
}
