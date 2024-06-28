package org.jola.learning.controller;

import jakarta.validation.ConstraintViolationException;
import org.jola.learning.dto.ArticleAddedResponseDto;
import org.jola.learning.dto.ArticleDto;
import org.jola.learning.service.ArticleService;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping(value = "/articles")
    public ResponseEntity<List<ArticleDto>> getArticles(
            @RequestParam(name = "authorId", required = false) Long authorId,
            @RequestParam(name = "authorAlias", required = false) String authorAlias
    ) {
        List<ArticleDto> articleList;

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
    public ResponseEntity<ArticleAddedResponseDto> addArticle(@RequestBody ArticleDto article) {
        // Job of controller:
        // 1. Validate the inputs and payload
        // 2. Call the service
        // 3. Handle the exceptions from the service and map it to relevant HTTP response
        Logger logger = Logger.getAnonymousLogger();
        try {
            ArticleDto articleDto = articleService.addArticle(article);
            ArticleAddedResponseDto articleAddedResponseDto = new ArticleAddedResponseDto(
                    articleDto,
                    "Article was successfully created"
            );
            return new ResponseEntity<>(articleAddedResponseDto, HttpStatus.OK);
        } catch (ConstraintViolationException exp) {
            // one or more fields violate constraints on the fields
            logger.log(Level.SEVERE, "ConstraintViolationException was thrown");
            ArticleAddedResponseDto articleAddedResponseDto = new ArticleAddedResponseDto(
                    null,
                    "ConstraintViolationException: Article could not be created due to validation fail"

            );
            return new ResponseEntity<>(articleAddedResponseDto, HttpStatus.BAD_REQUEST);
        } catch (PSQLException exp) {
            // database throws exception when violating unique constraint
            logger.log(Level.SEVERE, "PSQLException was thrown");
            ArticleAddedResponseDto articleAddedResponseDto = new ArticleAddedResponseDto(
                    null,
                    "duplicate key value violates unique constraint: title already exists"
            );
            return new ResponseEntity<>(articleAddedResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "/authors/{id}/articles",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ArticleAddedResponseDto> updateArticle(@RequestBody ArticleDto article) {
        Logger logger = Logger.getAnonymousLogger();
        try {
            ArticleDto articleDto = articleService.updateArticle(article);
            ArticleAddedResponseDto articleAddedResponseDto = new ArticleAddedResponseDto(
                    articleDto,
                    "Article was successfully updated"
            );
            return new ResponseEntity<>(articleAddedResponseDto, HttpStatus.OK);
        } catch (Exception exp) {
            logger.log(Level.SEVERE, "PSQLException was thrown");
            ArticleAddedResponseDto articleAddedResponseDto = new ArticleAddedResponseDto(
                    null,
                    "duplicate key value violates unique constraint: title already exists"
            );
            return new ResponseEntity<>(articleAddedResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(
            value = "/authors/{id}/articles",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> deleteArticle(@RequestBody ArticleDto article) {
        String response = articleService.deleteArticle(article);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private ResponseEntity<List<ArticleDto>> createResponseEntity(List<ArticleDto> list) {
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
