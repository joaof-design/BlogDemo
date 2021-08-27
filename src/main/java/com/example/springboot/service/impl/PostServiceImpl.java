package com.example.springboot.service.impl;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Post;
import com.example.springboot.repository.PostRepository;
import com.example.springboot.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    /**
     * Constructor dependency injection
     *
     * @param postRepository
     */
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public void updatePost(Long postId, Post post) {
        post.setId(postId);
        postRepository.save(post);
    }

    @Override
    public List<Post> getPostsByTitle(String title) {
        log.info("getPostsByTitle");
        return postRepository.findByTitle(title);
    }
}
