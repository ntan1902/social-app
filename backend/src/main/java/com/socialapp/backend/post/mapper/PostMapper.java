package com.socialapp.backend.post.mapper;

import com.socialapp.backend.post.dto.PostDTO;
import com.socialapp.backend.post.entity.Post;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PostMapper {
    PostDTO map(Post post);

    Post map(PostDTO postDTO);
}
