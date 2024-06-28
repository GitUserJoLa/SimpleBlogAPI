package org.jola.learning.repository;

import org.jola.learning.dto.AuthorDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//refactor methods to use AllIgnoreCase
public interface AuthorDtoRepository extends CrudRepository<AuthorDto, Long> {

    List<AuthorDto> findByAliasIgnoreCase(String alias);
    List<AuthorDto> findByFirstNameIgnoreCase(String firstName);
    List<AuthorDto> findByLastNameIgnoreCase(String lastName);
    List<AuthorDto> findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);
}
