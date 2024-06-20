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

    @GetMapping(value="/authors/{alias}/articles")
    public ResponseEntity<List<ArticleDto>> getAllArticlesByAuthorAlias(@PathVariable String alias){
        List<ArticleDto> articleList = articleService.getAllArticlesByAuthorAlias(alias);

        if (articleList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(articleList, HttpStatus.OK);
    }
}
