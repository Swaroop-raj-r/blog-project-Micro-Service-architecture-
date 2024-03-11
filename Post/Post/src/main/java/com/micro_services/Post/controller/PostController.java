package com.micro_services.Post.controller;

import com.micro_services.Post.payload.PostDto;
import com.micro_services.Post.service.impl.PostServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController
{
    @Autowired
    private PostServiceImpl postService;

    @PostMapping
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto dto)
    {
        PostDto postDto = postService.savePost(dto);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostDto> findPostById(@RequestParam String postId)
    {
        PostDto postById = postService.findPostById(postId);

        return new ResponseEntity<>(postById, HttpStatus.OK);
    }

    @GetMapping("{postId}/comment")
    @CircuitBreaker(name="commentBreaker", fallbackMethod = "commentFallback")
    public ResponseEntity<PostDto> getPostwithCommentsByPostId(@PathVariable String postId)
    {
       PostDto dto = postService.getPostwithCommentsByPostId(postId);

       return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    public ResponseEntity<PostDto> commentFallback(String postId, Exception ex)
    {
        System.out.print("Fallback is executed because service is down: "+ex.getMessage());

        PostDto dto = new PostDto();
        dto.setId("1234");
        dto.setTitle("server down");
        dto.setContent("server down");
        dto.setDescription("server down");
        return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);

    }
}
