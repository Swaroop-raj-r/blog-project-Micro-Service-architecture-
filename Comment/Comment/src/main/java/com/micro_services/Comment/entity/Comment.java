package com.micro_services.Comment.entity;



import jakarta.persistence.*;
import lombok.Data;

//import javax.persistence.*;


@Data
@Entity
public class Comment {
    @Id
    private String commentId;
    private String name;
    private String email;
    private String body;
    private String postId;
}
