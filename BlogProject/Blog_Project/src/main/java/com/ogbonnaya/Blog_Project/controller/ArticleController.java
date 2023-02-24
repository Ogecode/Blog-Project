package com.ogbonnaya.Blog_Project.controller;


import com.ogbonnaya.Blog_Project.dto.ArticleRequestDto;
import com.ogbonnaya.Blog_Project.model.Articles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ogbonnaya.Blog_Project.services.ArticleServices;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class ArticleController {
private final ArticleServices articleServices;

    public ArticleController(ArticleServices articleServices) {
        this.articleServices = articleServices;
    }


    @DeleteMapping( "/{articleId}")
    public ResponseEntity<?> deleteArticleByTitle(@PathVariable Integer articleId) {
        boolean deleted = articleServices.deleteArticleByTitle(articleId);
        if (deleted) {
            return new ResponseEntity<>("Article successfully deleted", HttpStatus.OK);
        }

        return new ResponseEntity<>("article with name not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping( "/{articleId}")
    public ResponseEntity<?> getArticleByTitle(@PathVariable Integer articleId) {
        Articles articles = articleServices.fetchArticlesByTitle(articleId);
        if (articles == null) {
            return new ResponseEntity<>("Article with author not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/get-article")
    public ResponseEntity<?> getAllArticles() {
        List<Articles> articles = articleServices.fetchAllArticle();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PostMapping("/create-article")
    public ResponseEntity<?> createArticles(@RequestBody ArticleRequestDto articleRequestDto) {
        Articles articles = articleServices.createArticle(articleRequestDto);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<?> updateArticles(@RequestBody ArticleRequestDto articleRequestDto, @PathVariable Integer articleId) {
        Articles articles = articleServices.updateArticle(articleRequestDto, articleId);
        if (articles == null) {
            return new ResponseEntity<>("Book with Id not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}


