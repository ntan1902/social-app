package com.socialapp.backend.user.service;


import com.socialapp.backend.authen.dto.LoginRequest;
import com.socialapp.backend.authen.dto.LoginResponse;
import com.socialapp.backend.authen.dto.RegisterRequest;
import com.socialapp.backend.user.dto.UserPostDTO;
import com.socialapp.backend.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO updateUser(Long id, UserDTO user);

    void deleteById(Long id);

    UserDTO findById(Long id);

    UserDTO findByUsername(String username);

    List<UserPostDTO> findAllPosts(Long id);

    List<UserDTO> findAllFollowings(Long id);

    List<UserPostDTO> findAllFriendPosts(Long id);

}
