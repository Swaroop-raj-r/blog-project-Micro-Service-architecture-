package com.micro_services.Post.entity;


//import javax.persistence.*;
import lombok.Data;
import jakarta.persistence.*;


@Entity
@Data
@Table(name="posts")
public class Post {

    @Id
    private String id;
    private String title;
    private String description;
    private String content;

}
