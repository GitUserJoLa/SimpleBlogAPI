package org.jola.learning.controller;

import org.jola.learning.dto.ArticleDto;
import org.jola.learning.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping(value="/articles")
    public ResponseEntity<List<ArticleDto>> getAllArticles(){
        List<ArticleDto> articleList = articleService.getAllArticles();
        return createResponseEntity(articleList);
    }

    @GetMapping(value="/authors/{id}/articles")
    public ResponseEntity<List<ArticleDto>> getAllArticlesByAuthorId(@PathVariable Long id){
        List<ArticleDto> articleList = articleService.getAllArticlesByAuthorId(id);
        return createResponseEntity(articleList);
    }

    @GetMapping(value="/authors/{alias}/articles")
    public ResponseEntity<List<ArticleDto>> getAllArticlesByAuthorAlias(@PathVariable String alias){
        List<ArticleDto> articleList = articleService.getAllArticlesByAuthorAlias(alias);
        return createResponseEntity(articleList);
    }

    private  ResponseEntity<List<ArticleDto>> createResponseEntity(List<ArticleDto> list){
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
