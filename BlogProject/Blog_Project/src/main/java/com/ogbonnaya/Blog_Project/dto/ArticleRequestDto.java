package com.ogbonnaya.Blog_Project.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequestDto {
    private String title;
    private String author;
    private String readTime;
}
