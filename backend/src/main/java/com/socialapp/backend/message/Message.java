package com.socialapp.backend.message;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long senderId;

    private Long receiverId;

    private String content;

    private LocalDateTime createdAt;

}

