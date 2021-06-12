package com.socialapp.backend.post.service.impl;

import com.socialapp.backend.post.entity.Post;
import com.socialapp.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostServiceImpl implements PostService {
//    private final PostRepository postRepository;
//    private final UserRepository userRepository;

    @Override
    public Post insertPost(Post post) {
//        log.info("Inside insertPost of PostServiceImpl");
//        User user = this.userRepository.findById(
//                post.getUser().getId()
//        )
//                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));
//
//        post.setUser(user);
//
//        // Return post of user without leaking password
//        Post res = this.postRepository.save(post);
//        res.getUser().setPassword(null);

        return null;
    }

    @Override
    public Post updatePost(Long id, Post post) {
//        log.info("Inside updatePost of PostServiceImpl");
//        this.postRepository.findById(id)
//                .orElseThrow(() -> new IllegalStateException("Invalid post id"));
//        this.userRepository.findById(post.getUser().getId())
//                .orElseThrow(() -> new UserIdNotFoundException("Invalid user id"));
//
//        Post res = this.postRepository.save(post);
//        res.getUser().setPassword(null);

        return null;
    }
}
