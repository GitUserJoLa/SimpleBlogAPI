package org.jola.learning.controller;

import org.jola.learning.dto.ArticleAddedResponseDto;
import org.jola.learning.dto.ArticleDto;
import org.jola.learning.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping(value = "/articles")
    public ResponseEntity<List<ArticleDto>> getArticles(
            @RequestParam(name = "authorId", required = false) Long authorId,
            @RequestParam(name = "authorAlias", required = false) String authorAlias
    ) {
        List<ArticleDto> articleList = Collections.emptyList();

        if (authorId == null && authorAlias == null)
            articleList = articleService.getAllArticles();
        else if (authorId != null)
            articleList = articleService.getAllArticlesByAuthorId(authorId);
        else
            articleList = articleService.getAllArticlesByAuthorAlias(authorAlias);

        return createResponseEntity(articleList);
    }

    @PostMapping(
            value = "/authors/{id}/articles",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ArticleDto> addArticle(@RequestBody ArticleDto article) {
        ArticleAddedResponseDto response = articleService.addArticle(article);
        return new ResponseEntity<>(response.getArticle(), HttpStatus.OK);
    }

    private ResponseEntity<List<ArticleDto>> createResponseEntity(List<ArticleDto> list) {
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
