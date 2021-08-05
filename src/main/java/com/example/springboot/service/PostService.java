package com.example.springboot.service;

import com.example.springboot.model.Post;

import java.util.List;

public interface PostService {

    public Post getPost(Long postId);

    public List<Post> getPosts();

    public Post createPost(Post post);

    public void deletePost(Long postId);

    public void updatePost(Long postId, Post post);
}
