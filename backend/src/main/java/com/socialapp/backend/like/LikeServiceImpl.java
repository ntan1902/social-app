package com.socialapp.backend.like;

import com.socialapp.backend.exception.user.ApiResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;

    @Override
    public void insertLike(Like like) {
        log.info("Inside insertLike of LikeServiceImpl");
        if (this.likeRepository.isUserLikedPost(like.getPostId(), like.getUserId())) {
            log.error("User is already liked");
            throw new ApiResponseException("User is already liked");
        }
        this.likeRepository.insert(like);
    }

    @Override
    public void deleteLike(Long id, Long userId) {
        log.info("Inside removeLike of LikeServiceImpl");
        if (!this.likeRepository.isUserLikedPost(id, userId)) {
            log.error("User is already unliked");
            throw new ApiResponseException("User is already unliked");
        }
        this.likeRepository.delete(id, userId);
    }
}
