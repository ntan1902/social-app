package com.socialapp.backend.user.controller;

import com.socialapp.backend.post.dto.UserPostDTO;
import com.socialapp.backend.user.dto.UserDTO;
import com.socialapp.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDTO userDTO) {
        UserDTO res = this.userService.updateUser(id, userDTO);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUserById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") Long id) {
        UserDTO res = this.userService.findUserById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/all-posts")
    public ResponseEntity<List<UserPostDTO>> findAllPosts(@PathVariable("id") Long id) {
        List<UserPostDTO> res = this.userService.findAllPosts(id);
        return ResponseEntity.ok(res);
    }
}
