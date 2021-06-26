package com.socialapp.backend.like.repository;

import com.socialapp.backend.like.entity.Like;

import java.util.List;
import java.util.Optional;

public interface LikeRepository {
    boolean isUserLikedPost(Long id, Long userId);

    void insert(Like like);

    void delete(Long id, Long userId);

    Optional<List<Like>> findAllByPostId(Long id);
}
