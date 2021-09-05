package com.socialapp.backend.user;


import com.socialapp.backend.authen.dto.RegisterRequest;
import com.socialapp.backend.authen.dto.TokenRefreshResponse;

import java.util.List;

public interface UserService {

    void updateUser(Long id, UserDTO user);

    void deleteById(Long id);

    UserDTO findById(Long id);

    UserDTO findByUsername(String username);

    List<UserPostDTO> findAllPosts(Long id);

    List<UserDTO> findAllFollowings(Long id);

    List<UserPostDTO> findAllFriendPosts(Long id);

    void register(RegisterRequest registerRequest);

    TokenRefreshResponse refreshToken(String token);
}
