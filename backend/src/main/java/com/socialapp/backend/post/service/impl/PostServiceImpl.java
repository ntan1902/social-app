package com.socialapp.backend.post.service.impl;

import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.post.dao.PostRepository;
import com.socialapp.backend.post.dto.PostDTO;
import com.socialapp.backend.post.entity.Post;
import com.socialapp.backend.post.mapper.PostMapper;
import com.socialapp.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    //    private final UserRepository userRepository;
    private final PostMapper postMapper;

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
}
