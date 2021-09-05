package com.socialapp.backend.post;

import java.util.List;
import java.util.Optional;

public interface PostRepository{
    int insert(Post post);

    int update(Post post);

    int deleteById(Long id);

    Optional<Post> findById(Long id);

    Optional<List<Post>> findAllByUserId(Long userId);

}
