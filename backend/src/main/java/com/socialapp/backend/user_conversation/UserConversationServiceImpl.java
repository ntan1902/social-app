package com.socialapp.backend.user_conversation;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserConversationServiceImpl implements UserConversationService {
    private final UserConversationRepository userConversationRepository;

    @Override
    public void insert(UserConversation userConversation) {
        log.info("Inside insert of UserConversationServiceImpl");

        this.userConversationRepository.insert(userConversation);
    }

    @Override
    public List<UserConversation> findAllByUserID(Long userId) {
        log.info("Inside findAllByUserID of UserConversationServiceImpl");

        return this.userConversationRepository.findAllByUserId(userId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid userConversation id"));
    }


    @Override
    public void delete(Long firstUserId, Long secondUserId) {
        log.info("Inside delete of UserConversationServiceImpl");
        this.userConversationRepository.delete(firstUserId, secondUserId);
    }


}

