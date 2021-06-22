package com.socialapp.backend.post.service.impl;

import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.like.dao.LikeRepository;
import com.socialapp.backend.like.entity.Like;
import com.socialapp.backend.post.dao.PostRepository;
import com.socialapp.backend.post.dto.PostDTO;
import com.socialapp.backend.post.entity.Post;
import com.socialapp.backend.post.mapper.PostMapper;
import com.socialapp.backend.post.service.PostService;
import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.dto.UserDTO;
import com.socialapp.backend.user.entity.User;
import com.socialapp.backend.user.mapper.UserMapper;
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
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    private final PostMapper postMapper;
    private final UserMapper userMapper;

    @Override
    public PostDTO insertPost(PostDTO postDTO) {
        log.info("Inside insertPost of PostServiceImpl");

        Post post = this.postMapper.map(postDTO);

        return this.postMapper.map(
                this.postRepository.insert(post)
                        .orElseThrow(() -> new ApiResponseException("Can't insert post"))
        );
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {

        log.info("Inside updatePost of PostServiceImpl");

        Post post = this.postMapper.map(postDTO);

        return this.postMapper.map(
                this.postRepository.update(post)
                        .orElseThrow(() -> new ApiResponseException("Can't update post"))
        );
    }

    @Override
    public void deletePostById(Long id) {
        log.info("Inside deletePost of PostServiceImpl");
        this.postRepository.deleteById(id);
    }

    @Override
    public PostDTO findPostById(Long id) {
        log.info("Inside findPostById of PostServiceImpl");

        return this.postMapper.map(
                this.postRepository.findById(id)
                        .orElseThrow(() -> new ApiResponseException("Invalid post id"))
        );
    }

    @Override
    public List<UserDTO> findLikedUsers(Long id) {
        log.info("Inside findLikedUsers of PostServiceImpl");

        List<UserDTO> res = new ArrayList<>();

        List<Like> likes = this.likeRepository.findAllByPostId(id)
                .orElse(Collections.emptyList());

        likes.forEach(like -> {
            User user = userRepository.findById(like.getUserId())
                    .orElse(null);
            res.add(
                    this.userMapper.map(user)
            );
        });

        return res;
    }

}
