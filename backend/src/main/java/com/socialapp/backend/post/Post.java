package com.socialapp.backend.post;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String description;

    private String img;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}