package com.example.springboot.service.impl;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Comment;
import com.example.springboot.repository.CommentRepository;
import com.example.springboot.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    /**
     * Constructor dependency injection
     *
     * @param commentRepository
     */
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void createComment(Comment comment) {
        this.commentRepository.save(comment);
    }

    @Override
    public Comment getCommment(Long commentId) {
        return this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment Id " + commentId + "was not found"));
    }

    @Override
    public List<Comment> getComments() {
        return this.commentRepository.findAll();
    }

    @Override
    public void deleteComment(Long commentId) {
        this.commentRepository.deleteById(commentId);
    }
}
