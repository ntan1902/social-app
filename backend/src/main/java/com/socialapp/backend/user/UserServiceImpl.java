package com.socialapp.backend.user;

import com.socialapp.backend.authen.dto.RegisterRequest;
import com.socialapp.backend.authen.dto.TokenRefreshResponse;
import com.socialapp.backend.authen.mapper.RegisterMapper;
import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.follow.FollowRepository;
import com.socialapp.backend.follow.Follow;
import com.socialapp.backend.like.LikeRepository;
import com.socialapp.backend.like.Like;
import com.socialapp.backend.post.PostRepository;
import com.socialapp.backend.post.Post;
import com.socialapp.backend.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FollowRepository followRepository;
    private final LikeRepository likeRepository;


    private final UserMapper userMapper;
    private final UserPostMapper userPostMapper;
    private final RegisterMapper registerMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // ---- UserDetailsService ----
    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("Inside loadUserByUsername of AuthenticationUserService");

        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiResponseException("User " + username + " is not found"));

    }
    // ---- UserDetailsService ----

    // Used by JwtAuthenticationFilter.class
    public User loadUserById(Long userId) {
        log.info("Inside loadUserById of AuthenticationUserService");

        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ApiResponseException("User id " + userId + " is not found"));
    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        log.info("Inside updateUser of UserServiceImpl");

        User user = userMapper.map(userDTO);

        int res = this.userRepository.update(user);
        if (res == 1) {
            log.info("Updated successfully");
        } else {
            log.error("Updated error");
        }
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

            User followingUser = userRepository.findById(follow.getFollowingId())
                    .orElse(null);
            res.add(userMapper.map(followingUser));
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

    @Override
    public void register(RegisterRequest registerRequest) {
        log.info("Inside register of UserServiceImpl");

        User user = registerMapper.map(registerRequest);
        user.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );
        user.setUserRole(UserRole.USER);

        int res = this.userRepository.insert(user);
        if (res == 1) {
            log.info("Register successfully");
        } else {
            log.error("Register error");
        }
    }

    @Override
    public TokenRefreshResponse refreshToken(String token) {
        log.info("Inside refreshToken of UserServiceImpl");

        if (jwtUtil.validateToken(token)) {
            Long userId = jwtUtil.getUserIdFromJwt(token);
            String jwt = jwtUtil.generateAccessToken(
                    this.loadUserById(userId)
            );
            return new TokenRefreshResponse(jwt, "Bearer");
        } else {
            throw new ApiResponseException("Invalid token");
        }

    }

    @Override
    public UserDTO findCurrentUserProfile() {
        log.info("Inside findCurrentUserProfile of UserServiceImpl");
        return this.userMapper.map(
                this.userRepository.findById(getCurrentUserId())
                        .orElseThrow(() -> new ApiResponseException("Invalid user id"))
        );
    }

    private Long getCurrentUserId() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}