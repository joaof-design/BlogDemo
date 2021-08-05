package com.example.springboot.service;

import com.example.springboot.model.Comment;

import java.util.List;

public interface CommentService {

    public void createComment(Comment comment);

    public Comment getCommment(Long commentId);

    public List<Comment> getComments();

    public void deleteComment(Long commentId);
}
