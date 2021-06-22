package com.socialapp.backend.follow.controller;

import com.socialapp.backend.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/follows")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PutMapping("/{id}")
    public ResponseEntity<?> insertFollow(@PathVariable("id") Long id ,@RequestParam("following-id") Long followingId){
        followService.insertFollow(id, followingId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFollow(@PathVariable("id") Long id ,@RequestParam("following-id") Long followingId){
        followService.removeFollow(id, followingId);
        return ResponseEntity.noContent().build();
    }
}
