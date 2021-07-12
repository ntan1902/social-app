package com.socialapp.backend.friend.controller;

import com.socialapp.backend.friend.entity.Friend;
import com.socialapp.backend.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friends")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class FriendController {
    private final FriendService friendService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Friend>> getFriends(@PathVariable("id") Long id) {
        List<Friend> res = friendService.findFriends(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Friend friend){
        friendService.insert(friend);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/{friend-id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id ,@PathVariable("friend-id") Long friendingId){
        friendService.delete(id, friendingId);
        return ResponseEntity.noContent().build();
    }
}
