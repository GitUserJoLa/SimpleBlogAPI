package org.jola.learning.controller;

import org.jola.learning.dto.AuthorDto;
import org.jola.learning.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<AuthorDto>> getAuthorByAlias(@PathVariable String alias) {

        List<AuthorDto> authorList = authorService.getAuthorByAlias(alias);

        if (authorList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(authorList, HttpStatus.OK);

        // throws 500:internal server errors due to non-case-sensitivity in search
        // if there are entries for (e.g.) udk and UDK db will return both which cannot be saved to an optional<>
//        Optional<AuthorDto> author = authorService.getAuthorByAlias(alias);
//        // map()-idiom is used with lists and list-like containers and expects a lambda function
//        return author.map(
//                        authorDto -> new ResponseEntity<>(authorDto, HttpStatus.OK)
//                ).
//                orElseGet(
//                        () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
//                );
    }


    // pay attention to case sensitivity in creation as searches in db are non-CS
    // design choice
//    @PostMapping(
//            value="/authors/signup",
//            consumes= MediaType.APPLICATION_JSON_VALUE)
//    public void addNewAuthor(@RequestBody AuthorDto author){
//        authorService.addNewAuthor(author);
//    }

}
