package com.socialapp.backend.follow;

import java.util.List;

public interface FollowService {
    void insert(Follow follow);

    void delete(Long id, Long followingId);

    List<Follow> findFollowings(Long id);
}
