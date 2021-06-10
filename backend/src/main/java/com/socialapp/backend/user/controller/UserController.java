package com.socialapp.backend.user.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.socialapp.backend.user.entity.User;
import com.socialapp.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        String hashedPassword = passwordEncoder.encode(
                user.getPassword()
        );
        user.setPassword(hashedPassword);

        User res = this.userService.updateUser(user);

        if (res != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        boolean res =  this.userService.deleteUser(id);

        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long id) {
        User user = this.userService.findUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<?> followUser(@PathVariable("id") Long id, @RequestBody Map<String, Long> userId) {
        boolean res = this.userService.followUser(id, userId.get("id"));

        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{id}/unfollow")
    public ResponseEntity<?> unfollowUser(@PathVariable("id") Long id, @RequestBody Map<String, Long> userId) {
        boolean res = this.userService.unfollowUser(id, userId.get("id"));

        if (res) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
