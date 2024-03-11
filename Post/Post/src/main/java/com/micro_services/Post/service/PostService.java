package com.micro_services.Post.service;

import com.micro_services.Post.payload.PostDto;

public interface PostService {
    PostDto savePost(PostDto dto);

    PostDto findPostById(String postId);

    PostDto getPostwithCommentsByPostId(String postId);
}
