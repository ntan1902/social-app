package com.socialapp.backend.user;

import com.socialapp.backend.authen.dto.RegisterRequest;
import com.socialapp.backend.authen.dto.TokenRefreshRequest;
import com.socialapp.backend.authen.dto.TokenRefreshResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
       this.userService.register(registerRequest);
        return new ResponseEntity<>("Register successfully", HttpStatus.CREATED);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        TokenRefreshResponse tokenRefreshResponse = this.userService.refreshToken(refreshToken);

        return ResponseEntity.ok(tokenRefreshResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDTO userDTO) {
        this.userService.updateUser(id, userDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") Long id) {
        UserDTO res = this.userService.findById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<UserDTO> findUserByUsername(@RequestParam("username") String username) {
        UserDTO res = this.userService.findByUsername(username);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<UserPostDTO>> findAllPosts(@PathVariable("id") Long id) {
        List<UserPostDTO> res = this.userService.findAllPosts(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/timeline")
    public ResponseEntity<List<UserPostDTO>> findTimeLine(@PathVariable("id") Long id) {
        List<UserPostDTO> res = this.userService.findAllFriendPosts(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/followings")
    public ResponseEntity<List<UserDTO>> findAllFollowings(@PathVariable("id") Long id) {
        List<UserDTO> res = this.userService.findAllFollowings(id);
        return ResponseEntity.ok(res);
    }
}
