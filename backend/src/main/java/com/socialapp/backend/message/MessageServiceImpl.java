package com.socialapp.backend.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public void insert(MessageDTO messageDTO) {
        log.info("Inside insert of MessageServiceImpl");

        Message message = this.messageMapper.map(messageDTO);

        this.messageRepository.insert(message);
    }

    @Override
    public List<MessageDTO> findBySenderIdOrReceiverId(Long senderId, Long receiverId) {
        log.info("Inside findBySenderIdOrReceiverId of MessageServiceImpl {}, {}", senderId, receiverId);

        List<Message> messages = this.messageRepository.findBySenderIdOrReceiverId(senderId, receiverId)
                .orElse(Collections.emptyList());

        return messages.stream()
                .map(this.messageMapper::map)
                .collect(Collectors.toList());
    }

}

