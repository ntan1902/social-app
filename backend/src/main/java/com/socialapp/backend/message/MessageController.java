package com.socialapp.backend.message;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user-messages")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageDTO> insert(@RequestBody MessageDTO messageDTO) throws URISyntaxException {
        MessageDTO res = this.messageService.insert(messageDTO);
        return ResponseEntity.created(new URI("/api/v1/user-messages/" + res.getId()))
                .body(res);
    }

    @GetMapping("/{sender-id}/{receiver-id}")
    public ResponseEntity<List<MessageDTO>> findById(@PathVariable("sender-id") Long senderId,
                                               @PathVariable("receiver-id") Long receiverId) {
        List<MessageDTO> res = this.messageService.findBySenderIdOrReceiverId(senderId, receiverId);
        return ResponseEntity.ok(res);
    }


}

