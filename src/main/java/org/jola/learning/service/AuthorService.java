package org.jola.learning.service;

import org.jola.learning.dto.AuthorDto;
import org.jola.learning.repository.AuthorDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorService {

    @Autowired
    private AuthorDtoRepository authorRepository;

    public List<AuthorDto> getAuthors(String lastName, String firstName) {

        if (firstName != null && lastName != null)
            return authorRepository.findByFirstNameAndLastNameAllIgnoreCase(firstName, lastName);
        else if (firstName != null)
            return authorRepository.findByFirstNameAllIgnoreCase(firstName);
        else if (lastName != null)
            return authorRepository.findByLastNameAllIgnoreCase(lastName);
        else {
            List<AuthorDto> authorList = new ArrayList<>();
            authorRepository.findAll()
                    .forEach(authorList::add);
            return authorList;
        }
    }

    public List<AuthorDto> getAuthorByAlias(String alias) {

        return authorRepository.findByAliasAllIgnoreCase(alias);
    }

//    public void addNewAuthor(AuthorDto author) {
//        //check for email. if present in db refuse to save dataset
//        //check for alias. if present in db refuse to save dataset
//        authorRepository.save(author);
//    }


}
