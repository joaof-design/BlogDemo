package com.example.springboot.service;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Post;
import com.example.springboot.repository.PostRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Log4j2
public class PostService {

    @Value("${share.dir}")
    private String shareDir;

    private final PostRepository postRepository;
    private final FileService fileService;

    /**
     * Constructor dependency injection
     *
     * @param postRepository
     */
    public PostService(PostRepository postRepository, FileService fileService) {
        this.postRepository = postRepository;
        this.fileService = fileService;
    }

    @Cacheable(value = "posts", key = "#postId")
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public void updatePost(Long postId, Post post) {
        post.setId(postId);
        postRepository.save(post);
    }

    public List<Post> getPostsByTitle(String title) {
        log.debug("getPostsByTitle");
        return postRepository.findByTitle(title);
    }

    public void createPostsByFile(String filename) throws IOException {
        log.debug("createPostsByFile");
        Instant begin = Instant.now();

        CompletableFuture.supplyAsync(() -> {
                    try {
                        return fileService.parseCSVFile(filename);
                    } catch (IOException e) {
                        throw new RuntimeException("Error in parsing file");
                    }
                })
                .thenApply(list -> postRepository.saveAll(list));

        Instant end = Instant.now();
        log.debug("createPostsByFile Duration: " + Duration.between(begin, end));
    }
}
