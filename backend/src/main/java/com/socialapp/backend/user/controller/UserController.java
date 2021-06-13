package com.socialapp.backend.user.controller;

import com.socialapp.backend.user.dto.UserDTO;
import com.socialapp.backend.user.entity.User;
import com.socialapp.backend.user.mapper.UserMapper;
import com.socialapp.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        User user = this.userMapper.map(userDTO);
        UserDTO res = this.userMapper.map(
                this.userService.updateUser(id, user)
        );
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long id) {
        UserDTO userDTO = this.userMapper.map(
                this.userService.findUserById(id)
        );
        return new ResponseEntity<>(userDTO, HttpStatus.FOUND);
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<?> followUser(@PathVariable("id") Long id, @RequestBody Map<String, Long> userId) {
        this.userService.followUser(id, userId.get("id"));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/unfollow")
    public ResponseEntity<?> unfollowUser(@PathVariable("id") Long id, @RequestBody Map<String, Long> userId) {
        this.userService.unfollowUser(id, userId.get("id"));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
