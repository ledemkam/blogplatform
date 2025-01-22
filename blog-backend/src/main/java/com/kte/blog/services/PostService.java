package com.kte.blog.services;


import com.kte.blog.domain.entities.Post;
import com.kte.blog.domain.entities.User;
import com.kte.blog.domain.enumeration.CreatePostRequest;
import com.kte.blog.domain.enumeration.UpdatePostRequest;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post getPost(UUID id);
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
    void deletePost(UUID id);
}
