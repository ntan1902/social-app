package com.socialapp.backend.follow.service;

import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.follow.repository.FollowRepository;
import com.socialapp.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Override
    public void insertFollow(Long id, Long followingId) {
        checkValidUserId(id, followingId);

        if (this.followRepository.isUserInFollowings(id, followingId)) {
            log.error("User is already followed");
            throw new ApiResponseException("User is already followed");
        }

        this.followRepository.insert(id, followingId);
    }

    @Override
    public void removeFollow(Long id, Long followingId) {
        checkValidUserId(id, followingId);

        if (!this.followRepository.isUserInFollowings(id, followingId)) {
            log.error("User is already unfollowed");
            throw new ApiResponseException("User is already unfollowed");
        }

        this.followRepository.remove(id, followingId);
    }

    private void checkValidUserId(Long id, Long followingId) {
        if (id.equals(followingId)) {
            log.error("Can not follow yourself");
            throw new ApiResponseException("Can not follow yourself");
        }

        this.userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Invalid user id");
                    throw new ApiResponseException("Invalid user id");
                });
        this.userRepository.findById(followingId)
                .orElseThrow(() -> {
                    log.error("Invalid user id");
                    throw new ApiResponseException("Invalid user id");
                });
    }
}
