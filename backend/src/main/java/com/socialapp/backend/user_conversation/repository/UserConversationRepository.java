package com.socialapp.backend.user_conversation.repository;

import com.socialapp.backend.user_conversation.entity.UserConversation;

import java.util.List;
import java.util.Optional;

public interface UserConversationRepository{
    void insert(UserConversation userConversation);

    void delete(Long firstUserId, Long secondUserId);

    Optional<List<UserConversation>> findAllByUserId(Long userId);
}

