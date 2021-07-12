package com.socialapp.backend.friend.service;

import com.socialapp.backend.friend.entity.Friend;

import java.util.List;

public interface FriendService {
    void insert(Friend friend);

    void delete(Long id, Long friendId);

    List<Friend> findFriends(Long id);
}
