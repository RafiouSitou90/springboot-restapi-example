package com.rafdev.springboot.restapi.example.service;

import com.rafdev.springboot.restapi.example.entity.Post;

import java.util.List;

public interface PostService
{
    Post savePost(Post post);

    List<Post> getPosts();

    Post updatePost(Long id, Post post);

    Post getPostById(Long id);

    void deletePostById(Long id);
}
