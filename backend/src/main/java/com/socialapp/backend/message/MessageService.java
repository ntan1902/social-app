package com.socialapp.backend.message;

import java.util.List;

public interface MessageService {
    void insert(MessageDTO messageDTO);

    List<MessageDTO> findBySenderIdOrReceiverId(Long senderId, Long receiverId);
}

