package org.jola.learning.service;

import org.jola.learning.dto.ArticleAddedResponseDto;
import org.jola.learning.dto.ArticleDto;
import org.jola.learning.repository.ArticleDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public ArticleAddedResponseDto addArticle(ArticleDto article) {
//        article = articleRepository.save(article);
        return new ArticleAddedResponseDto(
                articleRepository.save(article),
                "200");
    }

//    public ArticleAddedResponseDto addArticle(ArticleDto article) {
//
//        Logger logger = Logger.getAnonymousLogger();
//        ArticleAddedResponseDto responseDto;
//        try {
//            // throws (RuntimeException) IllegalArgumentException -
//            // in case the given entity is null
//            article = articleRepository.save(article);
//            responseDto = new ArticleAddedResponseDto(
//                    article,
//                    HttpStatus.OK
//            );
//        } catch (IllegalArgumentException ex) {
//            responseDto = new ArticleAddedResponseDto(
//                    article,
//                    HttpStatus.BAD_REQUEST
//            );
//
//            logger.log(Level.SEVERE,
//                    "could not save " + ArticleDto.class + " to database",
//                    ex);
//        }
//        return responseDto;
//    }

}
