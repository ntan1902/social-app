package com.socialapp.backend.user.dto;

import com.socialapp.backend.post.dto.PostDTO;
import com.socialapp.backend.user.dto.UserDTO;
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
