package com.socialapp.backend.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;

    private long userId;

    private String description;

    private String img;

    private String createdAt;

    private String updatedAt;

}