package com.socialapp.backend.user;


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
