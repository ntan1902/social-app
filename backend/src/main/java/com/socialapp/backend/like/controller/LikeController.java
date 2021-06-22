package com.socialapp.backend.like.controller;

import com.socialapp.backend.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PutMapping("/{id}")
    public ResponseEntity<?> insertLike(@PathVariable("id") Long id , @RequestParam("user-id") Long userId){
        likeService.insertLike(id, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFollow(@PathVariable("id") Long id ,@RequestParam("user-id") Long userId){
        likeService.removeLike(id, userId);
        return ResponseEntity.noContent().build();
    }
}
