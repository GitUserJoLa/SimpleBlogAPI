package org.jola.learning.controller;

import org.jola.learning.dto.AuthorDto;
import org.jola.learning.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(value = "/authors")
    public ResponseEntity<List<AuthorDto>> getAuthors(
            @RequestParam(name = "lastname", required = false) String lastName,
            @RequestParam(name = "firstname", required = false) String firstName
    ) {
        List<AuthorDto> authorList = authorService.getAuthors(lastName, firstName);

        if (authorList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @GetMapping(value = "/authors/{alias}")
    public ResponseEntity<AuthorDto> getAuthorByAlias(@PathVariable String alias) {
        Optional<AuthorDto> author = authorService.getAuthorByAlias(alias);
        // map()-idiom is used with lists and list-like containers and expects a lambda function
        return author.map(
                        authorDto -> new ResponseEntity<>(authorDto, HttpStatus.OK)
                ).
                orElseGet(
                        () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
                );
    }

}