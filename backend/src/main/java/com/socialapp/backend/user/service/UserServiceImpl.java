package com.socialapp.backend.user.service;

import com.socialapp.backend.authen.dto.RegisterRequest;
import com.socialapp.backend.authen.mapper.RegisterMapper;
import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.follow.repository.FollowRepository;
import com.socialapp.backend.follow.entity.Follow;
import com.socialapp.backend.like.repository.LikeRepository;
import com.socialapp.backend.like.entity.Like;
import com.socialapp.backend.post.repository.PostRepository;
import com.socialapp.backend.user.dto.UserPostDTO;
import com.socialapp.backend.post.entity.Post;
import com.socialapp.backend.user.mapper.UserPostMapper;
import com.socialapp.backend.user.repository.UserRepository;
import com.socialapp.backend.user.dto.UserDTO;
import com.socialapp.backend.user.entity.User;
import com.socialapp.backend.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FollowRepository followRepository;
    private final LikeRepository likeRepository;

    private final PasswordEncoder passwordEncoder;

    private final RegisterMapper registerMapper;
    private final UserMapper userMapper;
    private final UserPostMapper userPostMapper;

    // ---- UserService ----
    @Override
    public UserDTO insertUser(RegisterRequest registerRequest) {
        log.info("Inside insertUser of UserServiceImpl");

        User user = registerMapper.map(registerRequest);
        user.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );

        return this.userMapper.map(
                this.userRepository.insert(user)
                        .orElseThrow(() -> new ApiResponseException("Can't insert user"))
        );
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        log.info("Inside updateUser of UserServiceImpl");

        User user = userMapper.map(userDTO);

        return this.userMapper.map(
                this.userRepository.update(user)
                        .orElseThrow(() -> new ApiResponseException("Can't update user"))
        );
    }

    @Override
    public void deleteById(Long id) {
        log.info("Inside deleteUser of UserServiceImpl");
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDTO findById(Long id) {
        log.info("Inside findById of UserServiceImpl");

        return this.userMapper.map(
                this.userRepository.findById(id)
                        .orElseThrow(() -> new ApiResponseException("Invalid user id"))
        );
    }

    @Override
    public UserDTO findByUsername(String username) {
        log.info("Inside findByUsername of UserServiceImpl");

        return this.userMapper.map(
                this.userRepository.findByUsername(username)
                        .orElseThrow(() -> new ApiResponseException("Invalid username"))
        );
    }

    @Override
    public List<UserPostDTO> findAllPosts(Long id) {
        log.info("Inside findAllPosts of UserServiceImpl");
        List<UserPostDTO> res = new ArrayList<>();


        // Get all posts of user
        List<Post> posts = postRepository.findAllByUserId(id)
                .orElse(Collections.emptyList());

        // For each post, get all likes of that post
        posts.forEach(post -> {
            List<Like> likes = likeRepository.findAllByPostId(post.getId())
                    .orElse(Collections.emptyList());

            // For each like, get id of liked user
            List<Long> likedUsers = new ArrayList<>();
            likes.forEach(like -> likedUsers.add(like.getUserId()));

            res.add(
                    userPostMapper.map(post, likedUsers)
            );
        });
        return res;
    }

    @Override
    public List<UserDTO> findAllFollowings(Long id) {
        log.info("Inside findAllFollowings of UserServiceImpl");
        List<UserDTO> res = new ArrayList<>();


        // Get all follows of user
        List<Follow> follows = followRepository.findAllByUserId(id)
                .orElse(Collections.emptyList());

        // For each follow, get all information of following user
        follows.forEach(follow -> {

            User following_user = userRepository.findById(follow.getFollowingId())
                    .orElse(null);
            res.add(userMapper.map(following_user));
        });
        return res;
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