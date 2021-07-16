package com.socialapp.backend.like;

public interface LikeService {
    void insertLike(Like like);

    void deleteLike(Long id, Long userId);
}
