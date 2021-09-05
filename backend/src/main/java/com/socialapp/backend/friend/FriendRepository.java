package com.socialapp.backend.friend;

import java.util.List;
import java.util.Optional;

public interface FriendRepository{
    int insert(Friend friend);

    int delete(Long id, Long friendId);

    boolean isUserInFriends(Long id, Long friendId);

    Optional<List<Friend>> findAllByUserId(Long id);
}


