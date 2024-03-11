package com.micro_services.Comment.controller;

import com.micro_services.Comment.payload.CommentDto;
import com.micro_services.Comment.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping()
    public ResponseEntity<CommentDto>saveComment(@RequestBody CommentDto dto)
    {
        CommentDto commentDto = commentService.saveComment(dto);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@RequestParam String postId)
    {
        List<CommentDto> commentByPostId = commentService.getCommentByPostId(postId);
        return new ResponseEntity<>(commentByPostId,HttpStatus.OK);
    }

}
