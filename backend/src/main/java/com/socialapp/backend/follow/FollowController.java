package com.socialapp.backend.follow;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follows")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class FollowController {
    private final FollowService followService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Follow>> getFollowings(@PathVariable("id") Long id) {
        List<Follow> res = followService.findFollowings(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<?> insertFollow(@RequestBody Follow follow){
        followService.insert(follow);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/{following-id}")
    public ResponseEntity<?> deleteFollow(@PathVariable("id") Long id ,@PathVariable("following-id") Long followingId){
        followService.delete(id, followingId);
        return ResponseEntity.noContent().build();
    }
}
