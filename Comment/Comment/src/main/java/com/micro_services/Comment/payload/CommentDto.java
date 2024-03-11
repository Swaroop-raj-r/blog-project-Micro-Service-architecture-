package com.micro_services.Comment.payload;

import lombok.Data;

@Data
public class CommentDto {

    private String commentId;
    private String name;
    private String email;
    private String body;
    private String postId;
}
