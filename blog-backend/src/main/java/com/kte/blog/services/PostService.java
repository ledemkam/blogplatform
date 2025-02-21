package com.kte.blog.services;

import com.kte.blog.domain.entities.Post;
import com.kte.blog.domain.entities.User;
import com.kte.blog.domain.request.CreatePostRequest;
import com.kte.blog.domain.request.UpdatePostRequest;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);

}
