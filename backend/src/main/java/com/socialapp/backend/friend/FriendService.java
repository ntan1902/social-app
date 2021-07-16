package com.socialapp.backend.friend;

import java.util.List;

public interface FriendService {
    void insert(Friend friend);

    void delete(Long id, Long friendId);

    List<Friend> findFriends(Long id);
}
