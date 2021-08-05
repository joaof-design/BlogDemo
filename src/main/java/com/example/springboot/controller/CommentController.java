package com.example.springboot.controller;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Comment;
import com.example.springboot.repository.CommentRepository;
import com.example.springboot.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api")
public class CommentController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /**
     * Constructor dependency injection
     *
     * @param commentRepository
     * @param postRepository
     */
    public CommentController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
        return new ResponseEntity<>(this.postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("The postId " + postId + " was not found"))
                .getComments(), HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable Long postId) {
        comment.setPost(this.postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("The postId " + postId + " was not found")));
        return new ResponseEntity<>(this.commentRepository.save(comment), HttpStatus.CREATED);
    }

    @PutMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @PathVariable Long postId,
                                                 @RequestBody Comment commentRequest) {

        return new ResponseEntity<>(commentRepository.findById(commentId).map(comment -> {
                    comment.setText(commentRequest.getText());
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"))
                , HttpStatus.OK);
    }
}
