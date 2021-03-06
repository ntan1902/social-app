package com.socialapp.backend.like;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class LikeController {
    private final LikeService likeService;

    @PostMapping()
    public ResponseEntity<?> insertLike(@RequestBody Like like){
        likeService.insertLike(like);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/{user-id}")
    public ResponseEntity<?> deleteLike(@PathVariable("id") Long id, @PathVariable("user-id") Long userId){
        likeService.deleteLike(id, userId);
        return ResponseEntity.noContent().build();
    }
}
