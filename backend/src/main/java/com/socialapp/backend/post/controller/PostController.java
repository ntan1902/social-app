package com.socialapp.backend.post.controller;

import com.socialapp.backend.post.dto.PostDTO;
import com.socialapp.backend.post.dto.UserPostDTO;
import com.socialapp.backend.post.service.PostService;
import com.socialapp.backend.user.dto.UserDTO;
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
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO postDTO) {
        PostDTO res = this.postService.updatePost(id, postDTO);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        this.postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPost(@PathVariable("id") Long id) {
        PostDTO res = this.postService.findPostById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/liked-users")
    public ResponseEntity<List<UserDTO>> findLikedUsers(@PathVariable("id") Long id) {
        List<UserDTO> res = this.postService.findLikedUsers(id);
        return ResponseEntity.ok(res);
    }

}
