package com.socialapp.backend.user.mapper;

import com.socialapp.backend.user.dto.UserDTO;
import com.socialapp.backend.user.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    UserDTO map(User user);

    User map(UserDTO userDTO);
}
