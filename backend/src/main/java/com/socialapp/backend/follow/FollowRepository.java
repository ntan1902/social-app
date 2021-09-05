package com.socialapp.backend.follow;

import java.util.List;
import java.util.Optional;

public interface FollowRepository {
    int insert(Follow follow);

    int delete(Long id, Long followingId);

    boolean isUserInFollowings(Long id, Long userId);

    Optional<List<Follow>> findAllByUserId(Long id);

}
