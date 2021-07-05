package com.socialapp.backend.message.mapper;

import com.socialapp.backend.message.dto.MessageDTO;
import com.socialapp.backend.message.entity.Message;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MessageMapper {
    MessageDTO map(Message message);

    Message map(MessageDTO messageDTO);
}

