package com.example.springboot.controller;

import com.example.springboot.model.Post;
import com.example.springboot.service.FileService;
import com.example.springboot.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/api")
@Log4j2
public class PostController {

    @Value("${info.application.environment}")
    private String environment;

    private final PostService postService;

    private final FileService fileService;

    /**
     * Constructor dependency injection
     *
     * @param postService
     */
    public PostController(PostService postService, FileService fileService) {
        this.postService = postService;
        this.fileService = fileService;
    }


    @GetMapping("/test")
    public ResponseEntity<String> getTestRestApi() {
        StringBuilder sb = new StringBuilder();
        sb.append("test restapi (");
        sb.append(environment + ")");
        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }


    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        log.info("GET Request - Post by postId:" + postId);
        log.info("Environment:");
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

    @PostMapping("/posts/{filename}")
    public ResponseEntity<String> createPostByFilename(@PathVariable String filename) throws IOException {
        this.postService.createPostsByFile(filename);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
