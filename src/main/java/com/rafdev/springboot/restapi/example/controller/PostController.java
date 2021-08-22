package com.rafdev.springboot.restapi.example.controller;

import com.rafdev.springboot.restapi.example.entity.Post;
import com.rafdev.springboot.restapi.example.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController
{
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<Post> savePost(@Valid @RequestBody Post post) {
        return new ResponseEntity<Post>(postService.savePost(post), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @Valid @RequestBody Post post) {
        return new ResponseEntity<Post>(postService.updatePost(id, post), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        return new ResponseEntity<Post>(postService.getPostById(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long id) {
        postService.deletePostById(id);

        return new ResponseEntity<String>("Post deleted successfully!", HttpStatus.OK);
    }
}
