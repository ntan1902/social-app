package com.socialapp.backend.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private long id;

    private long userId;

    private String description;

    private String img;

    private String createdAt;

    private String updatedAt;

}
