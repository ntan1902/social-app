package com.socialapp.backend.friend;

import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class FriendServiceImpl implements FriendService{
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Override
    public void insert(Friend friend) {
        log.info("Inside insert of FriendServiceImpl");
        checkValidUserId(friend.getUserId(), friend.getFriendId());

        if (this.friendRepository.isUserInFriends(friend.getUserId(), friend.getFriendId())) {
            log.error("User is already friended");
            throw new ApiResponseException("User is already friended");
        }

        this.friendRepository.insert(friend);
    }

    @Override
    public void delete(Long id, Long friendId) {
        log.info("Inside delete of FriendServiceImpl");
        checkValidUserId(id, friendId);

        if (!this.friendRepository.isUserInFriends(id, friendId)) {
            log.error("User is already unfriended");
            throw new ApiResponseException("User is already unfriended");
        }

        this.friendRepository.delete(id, friendId);
    }

    @Override
    public List<Friend> findFriends(Long id) {
        log.info("Inside findFriends of FriendingServiceImpl");
        return this.friendRepository.findAllByUserId(id)
                .orElse(Collections.emptyList());
    }

    private void checkValidUserId(Long id, Long friendId) {
        if (id.equals(friendId)) {
            log.error("Can not friend yourself");
            throw new ApiResponseException("Can not friend yourself");
        }

        this.userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Invalid user id {}", id);
                    throw new ApiResponseException("Invalid user id " + id);
                });
        this.userRepository.findById(friendId)
                .orElseThrow(() -> {
                    log.error("Invalid user id {}", friendId);
                    throw new ApiResponseException("Invalid user id " + friendId);
                });
    }

}
