package com.ogbonnaya.Blog_Project.repositories;


import com.ogbonnaya.Blog_Project.model.Articles;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepositories extends JpaRepository<Articles, Integer> {
    List<Articles> findByAuthor(String author);

}
