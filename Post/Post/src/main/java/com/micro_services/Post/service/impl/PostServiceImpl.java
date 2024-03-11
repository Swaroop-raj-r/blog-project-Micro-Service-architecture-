package com.micro_services.Post.service.impl;

import com.micro_services.Post.config.RestTemplateConfig;
import com.micro_services.Post.entity.Post;
import com.micro_services.Post.payload.PostDto;
import com.micro_services.Post.repository.PostRepository;
import com.micro_services.Post.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private RestTemplateConfig restTemplate;
    @Autowired
    private PostRepository postRepo;

    @Autowired
    private ModelMapper modelmapper;

    @Override
    public PostDto savePost(PostDto dto)
    {
        Post post = mapToEntity(dto);
        post.setId(UUID.randomUUID().toString());
        Post save = postRepo.save(post);
        PostDto savedDto = mapToDto(save);
        return savedDto;
    }

    @Override
    public PostDto findPostById(String postId)
    {
        Post post = postRepo.findById(postId).get();
        return mapToDto(post);

    }
    @Override
    public PostDto getPostwithCommentsByPostId(String postId)
    {
        ArrayList forObject = restTemplate.getRestTemplate().getForObject("http://localhost:8082/api/comments?postId=" + postId, ArrayList.class);
        
        Post post = postRepo.findById(postId).get();
        PostDto dto = new PostDto();
        dto.setComments(forObject);
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());

        return dto;
    }

    Post mapToEntity(PostDto dto)
    {
        return modelmapper.map(dto, Post.class);
    }

    PostDto mapToDto(Post post)
    {
        return modelmapper.map(post, PostDto.class);
    }


}
