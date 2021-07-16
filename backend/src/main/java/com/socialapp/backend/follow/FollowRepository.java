package com.socialapp.backend.follow;

import java.util.List;
import java.util.Optional;

public interface FollowRepository {
    void insert(Follow follow);

    void delete(Long id, Long followingId);

    boolean isUserInFollowings(Long id, Long userId);

    Optional<List<Follow>> findAllByUserId(Long id);

}
