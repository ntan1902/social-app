package com.socialapp.backend.user;

import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.follow.FollowRepository;
import com.socialapp.backend.follow.Follow;
import com.socialapp.backend.like.LikeRepository;
import com.socialapp.backend.like.Like;
import com.socialapp.backend.post.PostRepository;
import com.socialapp.backend.post.Post;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FollowRepository followRepository;
    private final LikeRepository likeRepository;


    private final UserMapper userMapper;
    private final UserPostMapper userPostMapper;


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

    @Override
    public List<UserPostDTO> findAllFriendPosts(Long id) {
        log.info("Inside findAllPostsWithFriends of UserServiceImpl");

        // Get all posts of user
        List<UserPostDTO> res = new ArrayList<>(this.findAllPosts(id));

        // Get all friends of user
        List<Follow> follows = followRepository.findAllByUserId(id)
                .orElse(Collections.emptyList());

        // For each follow, get all posts of following user
        follows.forEach(follow -> {
            List<UserPostDTO> allPosts = this.findAllPosts(follow.getFollowingId());
            Collections.addAll(res, allPosts.toArray(new UserPostDTO[0]));
        });


        return res;
    }

}