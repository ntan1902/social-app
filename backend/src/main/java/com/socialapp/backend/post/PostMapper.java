package com.socialapp.backend.post;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PostMapper {
//    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
//    @Mapping(target = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    PostDTO map(Post post);

    Post map(PostDTO postDTO);
}
