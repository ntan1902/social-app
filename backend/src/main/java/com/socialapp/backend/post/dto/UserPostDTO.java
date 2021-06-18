package com.socialapp.backend.post.dto;

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
    private UserDTO user;
    private PostDTO post;
    private List<UserDTO> likes;
}
