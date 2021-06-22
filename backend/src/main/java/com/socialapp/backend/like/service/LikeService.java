package com.socialapp.backend.like.service;

public interface LikeService {
    void insertLike(Long id, Long userId);

    void removeLike(Long id, Long userId);
}
