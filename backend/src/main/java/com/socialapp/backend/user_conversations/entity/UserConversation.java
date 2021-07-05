package com.socialapp.backend.user_conversations.entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserConversation implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long firstUserId;

    private Long secondUserId;
}

