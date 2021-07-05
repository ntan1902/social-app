package com.socialapp.backend.user_conversations.repository;

import com.socialapp.backend.user_conversations.entity.UserConversation;

import java.util.List;
import java.util.Optional;

public interface UserConversationRepository{
    void insert(UserConversation userConversation);

    void delete(Long firstUserId, Long secondUserId);

    Optional<List<UserConversation>> findAllByUserId(Long userId);
}

