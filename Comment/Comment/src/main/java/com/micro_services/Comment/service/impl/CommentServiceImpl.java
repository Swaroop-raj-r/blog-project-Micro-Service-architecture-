package com.micro_services.Comment.service.impl;

import com.micro_services.Comment.config.RestTemplateConfig;
import com.micro_services.Comment.entity.Comment;
import com.micro_services.Comment.payload.CommentDto;
import com.micro_services.Comment.payload.Post;
import com.micro_services.Comment.repository.CommentRepository;
import com.micro_services.Comment.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private RestTemplateConfig restTemplate;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto saveComment(CommentDto dto)
    {
        Post post= restTemplate.getRestTemplate().getForObject("http://localhost:8081/api/posts?postId=" + dto.getPostId(), Post.class);



        if(post!=null)
        {
            Comment comment = mapToEntity(dto);
            comment.setPostId(post.getId());
            comment.setCommentId(UUID.randomUUID().toString());
            Comment save = commentRepo.save(comment);
            CommentDto commentDto = mapToDto(save);
            return commentDto;
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<CommentDto> getCommentByPostId(String postId)
    {
        List<Comment> byPostId = commentRepo.findByPostId(postId);
        List<CommentDto> collect = byPostId.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
        return collect;
    }

    Comment mapToEntity(CommentDto dto)
    {
        return modelMapper.map(dto,Comment.class);
    }

    CommentDto mapToDto(Comment comment)
    {
        return modelMapper.map(comment,CommentDto.class);
    }


}
