package com.socialapp.backend.post.controller;

import com.socialapp.backend.post.dto.PostDTO;
import com.socialapp.backend.post.dto.UserPostDTO;
import com.socialapp.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> insertPost(@RequestBody @Valid PostDTO postDTO) {

        PostDTO res = this.postService.insertPost(postDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO postDTO) {
        PostDTO res = this.postService.updatePost(id, postDTO);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        this.postService.deletePostById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPost(@PathVariable("id") Long id) {
        PostDTO res = this.postService.findPostById(id);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}/like")
    public ResponseEntity<?> likePost(@PathVariable("id") Long id, @RequestBody Map<String, Long> userId){
        this.postService.likePost(id, userId.get("id"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/timeline")
    public ResponseEntity<?> getAllTimeLine(@RequestBody Map<String, Long> userId) {
        List<UserPostDTO> res = this.postService.findTimeLine(userId.get("id"));
        return ResponseEntity.ok(res);
    }
}
