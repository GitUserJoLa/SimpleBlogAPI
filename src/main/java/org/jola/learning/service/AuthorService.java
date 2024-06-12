package org.jola.learning.service;

import org.jola.learning.dto.AuthorDto;
import org.jola.learning.repository.AuthorDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorDtoRepository authorRepository;

    public List<AuthorDto> getAllAuthors(){
        List<AuthorDto> authorList = new ArrayList<>();
        authorRepository.findAll().forEach(authorList::add); //findAll() returns iterable, convert each element and add to list
        return authorList;
    }

    public Optional<AuthorDto> getAuthorByAlias(String alias) {
        return authorRepository.findByAlias(alias);
    }
}
