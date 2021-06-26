package com.socialapp.backend.follow.controller;

import com.socialapp.backend.follow.entity.Follow;
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

    @PostMapping
    public ResponseEntity<?> insertFollow(@RequestBody Follow follow){
        followService.insertFollow(follow);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/{following-id}")
    public ResponseEntity<?> deleteFollow(@PathVariable("id") Long id ,@PathVariable("following-id") Long followingId){
        followService.deleteFollow(id, followingId);
        return ResponseEntity.noContent().build();
    }
}
