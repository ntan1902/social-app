package com.socialapp.backend.post.controller;

import com.socialapp.backend.post.entity.Post;
import com.socialapp.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> insertPost(@RequestBody Post post) {
        Post res = this.postService.insertPost(post);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        Post res = this.postService.updatePost(id, post);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
