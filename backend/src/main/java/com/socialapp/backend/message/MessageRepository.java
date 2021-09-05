package com.socialapp.backend.message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository{
    int insert(Message message);

    Optional<Message> findById(Long id);

    Optional<List<Message>> findBySenderIdOrReceiverId(Long senderId, Long receiverId);

}

