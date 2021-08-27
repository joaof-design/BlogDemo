package com.example.springboot.controller;

import com.example.springboot.model.Post;
import com.example.springboot.service.impl.PostServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api")
@Log4j2
public class PostController {

    private final PostServiceImpl postService;

    /**
     * Constructor dependency injection
     *
     * @param postService
     */
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        log.info("GET Request - Post by postId:" + postId);
        return new ResponseEntity<>(this.postService.getPost(postId), HttpStatus.OK);
    }

    @GetMapping("/posts}")
    public ResponseEntity<List<Post>> getPostsByTitle(@RequestParam String title) {
        return new ResponseEntity<>(this.postService.getPostsByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return new ResponseEntity<>(this.postService.getPosts(), HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return new ResponseEntity<>(this.postService.createPost(post), HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public void updatePost(@RequestBody Post post, @PathVariable Long postId) {
        this.postService.updatePost(postId, post);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
