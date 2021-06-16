package com.socialapp.backend.post.dto;

import kotlin.jvm.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;

    @NotEmpty(message = "User id can not be empty")
    private long userId;

    private String description;

    private String img;

    private LocalDate createdAt;

    private LocalDate updatedAt;

}
