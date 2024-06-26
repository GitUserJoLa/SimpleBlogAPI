package org.jola.learning.service;

import org.jola.learning.dto.ArticleAddedResponseDto;
import org.jola.learning.dto.ArticleDto;
import org.jola.learning.repository.ArticleDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDtoRepository articleRepository;

    public List<ArticleDto> getAllArticles() {
        List<ArticleDto> articleList = new ArrayList<>();
        articleRepository.findAll()
                .forEach(
                        articleList::add
                );
        return articleList;

        // compiler warning: unchecked or unsafe operations
//        return new ArrayList<>((Collection) articleRepository.findAll());
    }

    public List<ArticleDto> getAllArticlesByAuthorId(Long id) {
        return new ArrayList<>(articleRepository.findByAuthorId(id));
    }

    public List<ArticleDto> getAllArticlesByAuthorAlias(String alias) {
        return new ArrayList<>(articleRepository.findByAuthorAliasIgnoreCase(alias));
    }

    public ArticleDto addArticle(ArticleDto article) {
        return articleRepository.save(article);
    }


    public ArticleDto updateArticle(ArticleDto article) {
        return addArticle(article);
    }

    public String deleteArticle(ArticleDto article) {
        articleRepository.delete(article);
        return "Article successfully deleted";
    }
}
