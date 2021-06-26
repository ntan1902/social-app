package com.socialapp.backend.like.service;

import com.socialapp.backend.like.entity.Like;

public interface LikeService {
    void insertLike(Like like);

    void deleteLike(Long id, Long userId);
}
