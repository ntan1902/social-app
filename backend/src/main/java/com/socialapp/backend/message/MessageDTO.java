package com.socialapp.backend.message;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO {
    private long id;

    private long senderId;

    private long receiverId;

    private String content;

    private String createdAt;
}

