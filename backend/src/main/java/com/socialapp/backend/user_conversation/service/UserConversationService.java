package com.socialapp.backend.user_conversation.service;

import com.socialapp.backend.user_conversation.entity.UserConversation;

import java.util.List;

public interface UserConversationService {
    void insert(UserConversation userConversation);

    List<UserConversation> findAllByUserID(Long userId);

    void delete(Long firstUserId, Long secondUserId);

}
