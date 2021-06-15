package com.socialapp.backend.user.service.impl;

import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.entity.User;
import com.socialapp.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ---- UserService ----
    @Override
    public User insertUser(User user) {
        log.info("Inside insertUser of UserServiceImpl");
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        try {
            return this.userRepository.insertUser(user).get();

        } catch (Exception e) {
            throw new ApiResponseException("Can't insert user");
        }
    }

    @Override
    public User updateUser(Long id, User user) {
        log.info("Inside updateUser of UserServiceImpl");

        try {
            return this.userRepository.updateUser(user).get();
        } catch (Exception e) {
            throw new ApiResponseException("Can't update user");
        }
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Inside deleteUser of UserServiceImpl");
        this.userRepository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        log.info("Inside findUserById of UserServiceImpl");

        try {
            return this.userRepository.findById(id).get();
        } catch (Exception e) {
            log.error("Invalid user id");
            throw new ApiResponseException("Invalid user id");
        }
    }

    @Override
    public void followUser(Long id, Long userId) {
        checkValidUserId(id, userId);

        if (this.userRepository.isUserInFollowings(id, userId)) {
            log.error("User is already followed");
            throw new ApiResponseException("User is already followed");
        }

        this.userRepository.insertFollow(id, userId);
    }


    @Override
    public void unfollowUser(Long id, Long userId) {
        checkValidUserId(id, userId);

        if (!this.userRepository.isUserInFollowings(id, userId)) {
            log.error("User is already unfollowed");
            throw new ApiResponseException("User is already unfollowed");
        }

        this.userRepository.removeFollow(id, userId);
    }

    private void checkValidUserId(Long id, Long userId) {
        if (id.equals(userId)) {
            log.error("Can not follow yourself");
            throw new ApiResponseException("Can not follow yourself");
        }

        this.userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Invalid user id");
                    throw new ApiResponseException("Invalid user id");
                });
        this.userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("Invalid user id");
                    throw new ApiResponseException("Invalid user id");
                });
    }
}