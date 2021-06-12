package com.socialapp.backend.user.service.impl;

import com.socialapp.backend.exception.user.UserCanNotFollowException;
import com.socialapp.backend.exception.user.UserIdNotFoundException;
import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.entity.User;
import com.socialapp.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


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
        return this.userRepository.insertUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Can't insert user"));
    }

    @Override
    public User updateUser(Long id, User user) {
        log.info("Inside updateUser of UserServiceImpl");
        this.userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));

        return this.userRepository.updateUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Can't update user"));
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Inside deleteUser of UserServiceImpl");
        this.userRepository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        log.info("Inside findUserById of UserServiceImpl");

        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));
    }

    @Override
    public void followUser(Long id, Long userId) {
        if (id.equals(userId)) {
            throw new UserCanNotFollowException("Can not follow yourself");
        }

        User currentUser = this.userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));
        User followedUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));

        List<User> followings = this.userRepository.findFollowings(id);
        if (followings.contains(followedUser)) {
            throw new UserCanNotFollowException("User is already followed");
        }

        this.userRepository.insertFollow(id, userId);

    }

    @Override
    public void unfollowUser(Long id, Long userId) {
        if (id.equals(userId)) {
            throw new UserCanNotFollowException("Can not follow yourself");
        }

        User currentUser = this.userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));
        User unfollowedUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));

        List<User> followings = this.userRepository.findFollowings(id);
        if (!followings.contains(unfollowedUser)) {
            throw new UserCanNotFollowException("User is already unfollowed");
        }

        this.userRepository.removeFollow(id, userId);
    }


}