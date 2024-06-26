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

import java.sql.SQLException;
import java.util.List;

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
        try {
            ArticleDto articleDto = articleService.addArticle(article);
            ArticleAddedResponseDto articleAddedResponseDto = new ArticleAddedResponseDto(
                    articleDto,
                    "Article was successfully created"
            );
            return new ResponseEntity<>(articleAddedResponseDto, HttpStatus.OK);
        } catch (ConstraintViolationException exp) {
            ArticleAddedResponseDto articleAddedResponseDto = new ArticleAddedResponseDto(
                    null,
                    "ConstraintViolationException: Article was not successfully created due to validation fail"

            );
            return new ResponseEntity<>(articleAddedResponseDto, HttpStatus.BAD_REQUEST);
        }
        // when trying to post an article (without id) which has the same title as an article already in db,
        // runtime says:
        // org.postgresql.util.PSQLException: ERROR: duplicate key value violates unique constraint "uk_1r3ncw58gm0na08iitnywbww1"
        //  Detail: Key (title)=(Sitting on sofas) already exists.

//        catch (PSQLException exp){
//            // compiler says:
//            // error: exception PSQLException is never thrown in body of corresponding try statement
//            ArticleAddedResponseDto articleAddedResponseDto = new ArticleAddedResponseDto(
//                    null,
//                    "duplicate key value violates unique constraint: title already exists"
//            );
//            return new ResponseEntity<>(articleAddedResponseDto, HttpStatus.BAD_REQUEST);
//        }
        catch (Exception exp) {
            exp.printStackTrace();
            // Caused by: org.postgresql.util.PSQLException: ERROR: duplicate key value violates unique constraint "uk_1r3ncw58gm0na08iitnywbww1"
            //  Detail: Key (title)=(Sitting on sofas) already exists.
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
        ArticleDto articleDto = articleService.updateArticle(article);
        ArticleAddedResponseDto articleAddedResponseDto = new ArticleAddedResponseDto(
                articleDto,
                "Article was successfully updated"
        );
        return new ResponseEntity<>(articleAddedResponseDto, HttpStatus.OK);
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
