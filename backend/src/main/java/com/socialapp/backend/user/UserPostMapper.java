package com.socialapp.backend.user;

import com.socialapp.backend.post.Post;
import com.socialapp.backend.post.PostMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {PostMapper.class})
public interface UserPostMapper {
    UserPostDTO map(Post data, List<Long> likes);
}
