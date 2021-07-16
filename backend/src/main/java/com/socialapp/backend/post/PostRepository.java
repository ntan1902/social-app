package com.socialapp.backend.post;

import java.util.List;
import java.util.Optional;

public interface PostRepository{
    Optional<Post> insert(Post post);

    Optional<Post> update(Post post);

    void deleteById(Long id);

    Optional<Post> findById(Long id);

    Optional<List<Post>> findAllByUserId(Long userId);

}
