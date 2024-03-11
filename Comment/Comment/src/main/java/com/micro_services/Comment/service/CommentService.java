package com.micro_services.Comment.service;

import com.micro_services.Comment.entity.Comment;
import com.micro_services.Comment.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto saveComment(CommentDto dto);

    List<CommentDto> getCommentByPostId(String postId);
}
