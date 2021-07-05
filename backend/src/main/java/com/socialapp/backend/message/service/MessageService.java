package com.socialapp.backend.message.service;

import com.socialapp.backend.message.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    MessageDTO insert(MessageDTO messageDTO);

    List<MessageDTO> findBySenderIdOrReceiverId(Long senderId, Long receiverId);
}

