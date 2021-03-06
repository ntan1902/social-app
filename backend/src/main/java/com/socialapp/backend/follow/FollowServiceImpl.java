package com.socialapp.backend.follow;

import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Override
    public void insert(Follow follow) {
        log.info("Inside insertFollow of FollowServiceImpl");
        checkValidUserId(follow.getUserId(), follow.getFollowingId());

        if (this.followRepository.isUserInFollowings(follow.getUserId(), follow.getFollowingId())) {
            log.error("User is already followed");
            throw new ApiResponseException("User is already followed");
        }

        this.followRepository.insert(follow);
    }

    @Override
    public void delete(Long id, Long followingId) {
        log.info("Inside deleteFollow of FollowServiceImpl");
        checkValidUserId(id, followingId);

        if (!this.followRepository.isUserInFollowings(id, followingId)) {
            log.error("User is already unfollowed");
            throw new ApiResponseException("User is already unfollowed");
        }

        this.followRepository.delete(id, followingId);
    }

    @Override
    public List<Follow> findFollowings(Long id) {
        log.info("Inside findFollowings of FollowingServiceImpl");
        return this.followRepository.findAllByUserId(id)
                .orElse(Collections.emptyList());
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
