package com.socialapp.backend.authen.mapper;

import com.socialapp.backend.authen.dto.RegisterRequest;
import com.socialapp.backend.user.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RegisterMapper {
    User map(RegisterRequest registerRequest);

}
