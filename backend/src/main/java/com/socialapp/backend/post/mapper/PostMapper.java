package com.socialapp.backend.post.mapper;

import com.socialapp.backend.post.dto.PostDTO;
import com.socialapp.backend.post.entity.Post;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PostMapper {
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    PostDTO map(Post post);

    Post map(PostDTO postDTO);
}
