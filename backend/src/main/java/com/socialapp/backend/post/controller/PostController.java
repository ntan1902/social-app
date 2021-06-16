package com.socialapp.backend.post.controller;

import com.socialapp.backend.post.dto.PostDTO;
import com.socialapp.backend.post.entity.Post;
import com.socialapp.backend.post.mapper.PostMapper;
import com.socialapp.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> insertPost(@RequestBody PostDTO postDTO) {

        PostDTO res = this.postService.insertPost(postDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO postDTO) {
        PostDTO res = this.postService.updatePost(id, postDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
