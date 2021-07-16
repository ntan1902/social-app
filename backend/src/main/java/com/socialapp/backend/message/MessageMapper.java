package com.socialapp.backend.message;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MessageMapper {
    MessageDTO map(Message message);

    Message map(MessageDTO messageDTO);
}

