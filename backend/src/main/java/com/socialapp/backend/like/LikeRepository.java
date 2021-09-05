package com.socialapp.backend.like;

import java.util.List;
import java.util.Optional;

public interface LikeRepository {
    boolean isUserLikedPost(Long id, Long userId);

    int insert(Like like);

    int delete(Long id, Long userId);

    Optional<List<Like>> findAllByPostId(Long id);
}
