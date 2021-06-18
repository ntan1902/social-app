package com.socialapp.backend.post.service.impl;

import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.post.dao.PostRepository;
import com.socialapp.backend.post.dto.PostDTO;
import com.socialapp.backend.post.dto.UserPostDTO;
import com.socialapp.backend.post.entity.Post;
import com.socialapp.backend.post.mapper.PostMapper;
import com.socialapp.backend.post.mapper.UserPostMapper;
import com.socialapp.backend.post.service.PostService;
import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;
    private final UserPostMapper userPostMapper;

    @Override
    public PostDTO insertPost(PostDTO postDTO) {
        log.info("Inside insertPost of PostServiceImpl");

        Post post = this.postMapper.map(postDTO);

        return this.postMapper.map(
                this.postRepository.insertPost(post)
                        .orElseThrow(() -> new ApiResponseException("Can't insert post"))
        );
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {

        log.info("Inside updatePost of PostServiceImpl");

        Post post = this.postMapper.map(postDTO);

        return this.postMapper.map(
                this.postRepository.updatePost(post)
                        .orElseThrow(() -> new ApiResponseException("Can't update post"))
        );
    }

    @Override
    public void deletePostById(Long id) {
        log.info("Inside deletePost of PostServiceImpl");
        this.postRepository.deletePostById(id);
    }

    @Override
    public PostDTO findPostById(Long id) {
        log.info("Inside findPostById of PostServiceImpl");

        return this.postMapper.map(
                this.postRepository.findPostById(id)
                        .orElseThrow(() -> new ApiResponseException("Invalid post id"))
        );
    }

    @Override
    public void likePost(Long id, Long userId) {
        log.info("Inside likePost of PostServiceImpl");
        if (this.postRepository.isUserLikedPost(id, userId)) {
            this.postRepository.removeLike(id, userId);
        } else {
            this.postRepository.insertLike(id, userId);
        }
    }

    @Override
    public List<UserPostDTO> findTimeLine(Long userId) {
        log.info("Inside findTimeLine of PostServiceImpl");
        List<UserPostDTO> res = new ArrayList<>();

        // Get User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiResponseException("Invalid user id"));

        // Get all posts of user
        List<Post> posts = postRepository.findPostsByUserId(userId)
                .orElse(Collections.emptyList());

        // For each post, get all likes of that post
        posts.forEach(post -> {
            List<User> likes = postRepository.findLikesOfPost(post.getId())
                    .orElse(Collections.emptyList());

            res.add(
                    userPostMapper.map(user, post, likes)
            );
        });
        return res;
    }
}
