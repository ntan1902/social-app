package com.socialapp.backend.user;

import com.socialapp.backend.post.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDTO {
    private PostDTO data;
    private List<Long> likes;
}
