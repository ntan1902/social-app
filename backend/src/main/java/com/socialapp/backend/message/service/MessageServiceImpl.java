package com.socialapp.backend.message.service;

import com.socialapp.backend.message.dto.MessageDTO;
import com.socialapp.backend.message.entity.Message;
import com.socialapp.backend.message.mapper.MessageMapper;
import com.socialapp.backend.message.repository.MessageRepository;
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
    public MessageDTO insert(MessageDTO messageDTO) {
        log.info("Inside insert of MessageServiceImpl");

        Message message = this.messageMapper.map(messageDTO);

        return this.messageMapper.map(
                this.messageRepository.insert(message)
                        .orElseThrow(() -> new IllegalArgumentException("Can't insert message"))
        );
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

