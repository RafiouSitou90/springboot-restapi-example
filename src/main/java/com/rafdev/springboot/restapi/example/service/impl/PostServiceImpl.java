package com.rafdev.springboot.restapi.example.service.impl;

import com.rafdev.springboot.restapi.example.entity.Post;
import com.rafdev.springboot.restapi.example.exception.ResourceNotFoundException;
import com.rafdev.springboot.restapi.example.repository.PostRepository;
import com.rafdev.springboot.restapi.example.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService
{
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));

        existingPost.setTitle(post.getTitle());
        existingPost.setSummary(post.getSummary());
        existingPost.setContent(post.getContent());
        existingPost.setPublishedAt(post.getPublishedAt());

        return postRepository.save(existingPost);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));

        postRepository.deleteById(id);
    }
}
