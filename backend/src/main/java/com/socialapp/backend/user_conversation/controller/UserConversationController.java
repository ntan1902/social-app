package com.socialapp.backend.user_conversation.controller;

import com.socialapp.backend.user_conversation.entity.UserConversation;
import com.socialapp.backend.user_conversation.service.UserConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user-conversations")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserConversationController {
    private final UserConversationService userConversationService;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserConversation userConversation) throws URISyntaxException {
        this.userConversationService.insert(userConversation);
        return ResponseEntity.created(new URI("/api/v1/user-conversations/" )).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UserConversation>> findAllByUserId(@PathVariable("id") Long userId) {
        List<UserConversation> res = this.userConversationService.findAllByUserID(userId);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{first-user-id}/{second-user-id}")
    public ResponseEntity<Void> delete(@PathVariable("first-user-id") Long firstUserId, @PathVariable("second-user-id") Long secondUserId) {
        this.userConversationService.delete(firstUserId, secondUserId);
        return ResponseEntity.noContent().build();
    }
}

