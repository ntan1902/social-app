package com.socialapp.backend.user.mapper;

import com.socialapp.backend.user.dto.UserPostDTO;
import com.socialapp.backend.post.entity.Post;
import com.socialapp.backend.post.mapper.PostMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {PostMapper.class})
public interface UserPostMapper {
    UserPostDTO map(Post data, List<Long> likes);
}
