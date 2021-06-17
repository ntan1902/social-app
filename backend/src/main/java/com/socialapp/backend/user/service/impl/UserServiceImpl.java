package com.socialapp.backend.user.service.impl;

import com.socialapp.backend.authen.dto.RegisterRequest;
import com.socialapp.backend.authen.mapper.RegisterMapper;
import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.dto.UserDTO;
import com.socialapp.backend.user.entity.User;
import com.socialapp.backend.user.mapper.UserMapper;
import com.socialapp.backend.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegisterMapper registerMapper;
    private final UserMapper userMapper;

    // ---- UserService ----
    @Override
    public UserDTO insertUser(RegisterRequest registerRequest) {
        log.info("Inside insertUser of UserServiceImpl");

        User user = registerMapper.map(registerRequest);
        user.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );

        return this.userMapper.map(
                this.userRepository.insertUser(user)
                        .orElseThrow(() -> new ApiResponseException("Can't insert user"))
        );
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        log.info("Inside updateUser of UserServiceImpl");

        User user = userMapper.map(userDTO);

        return this.userMapper.map(
                this.userRepository.updateUser(user)
                        .orElseThrow(() -> new ApiResponseException("Can't update user"))
        );
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Inside deleteUser of UserServiceImpl");
        this.userRepository.deleteUserById(id);
    }

    @Override
    public UserDTO findUserById(Long id) {
        log.info("Inside findUserById of UserServiceImpl");

        return this.userMapper.map(
                this.userRepository.findById(id)
                        .orElseThrow(() -> new ApiResponseException("Invalid user id"))
        );
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

    /*
     * Test SQL Injection
     */
    @Override
    public boolean loginUncheckInjection(String username, String password) {
        Optional<User> optionalUser = this.userRepository.findUserUncheckInjection(username, password);

        if (optionalUser.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean loginUncheckInjectionHashPassword(String username, String password) {
        Optional<User> optionalUser = this.userRepository.findByUsernameUncheckInjection(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

}