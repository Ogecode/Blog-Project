package com.ogbonnaya.Blog_Project.services;

import com.ogbonnaya.Blog_Project.dto.ArticleRequestDto;
import com.ogbonnaya.Blog_Project.model.Articles;
import org.springframework.stereotype.Service;
import com.ogbonnaya.Blog_Project.repositories.ArticleRepositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ArticleServices {

    private final ArticleRepositories articleRepositories;

    public ArticleServices(ArticleRepositories articleRepositories) {
        this.articleRepositories = articleRepositories;
    }


    public Articles createArticle(ArticleRequestDto articleRequestDto) {
        Articles articles = new Articles();
        articles.setAuthor(articleRequestDto.getAuthor());
        articles.setCreatedAt(LocalDate.now());
        articles.setTitle(articleRequestDto.getTitle());
        articles.setUpdatedAt(LocalDate.now());
        articles.setReadTime(articleRequestDto.getReadTime());
        articles = articleRepositories.save(articles);
        return articles;
    }

    public Articles updateArticle(ArticleRequestDto articleRequestDto, Integer id) {
        Optional<Articles> optionalArticles = articleRepositories.findById(id);
        if (optionalArticles.isEmpty()) {
            return null;
        }
        Articles articles = optionalArticles.get();
        articles.setUpdatedAt(LocalDate.now());
        articles.setReadTime(articleRequestDto.getReadTime());
        articles.setAuthor(articleRequestDto.getAuthor());
        articles.setTitle(articleRequestDto.getTitle());
        articles = articleRepositories.save(articles);

        return articles;
    }

    public Articles fetchArticlesByTitle(Integer Id) {
        Optional<Articles> optionalArticles = articleRepositories.findById(Id);
        if (optionalArticles.isEmpty()) {
            return null;
        }
        Articles articles = optionalArticles.get();
        return articles;
    }

    public List<Articles> fetchAllArticle() {
        List<Articles> articles = articleRepositories.findAll();
        return articles;
    }

    public List<Articles> fetchArticleByAuthor(String author) {
        List<Articles> articles = articleRepositories.findByAuthor(author);
        return articles;
    }


    public boolean deleteArticleByTitle(Integer articleId) {
        Optional<Articles> optionalArticles = articleRepositories.findById(articleId);
        if (optionalArticles.isEmpty()) {
            return false;
        }
        Articles articles = optionalArticles.get();
        articleRepositories.delete(articles);
        return true;
    }
}
