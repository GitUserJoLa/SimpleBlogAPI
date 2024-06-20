package org.jola.learning.repository;

import org.jola.learning.dto.AuthorDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//refactor methods to use AllIgnoreCase
public interface AuthorDtoRepository extends CrudRepository<AuthorDto, Long> {
    List<AuthorDto> findByAliasAllIgnoreCase(String alias);
    List<AuthorDto> findByFirstNameAllIgnoreCase(String firstName);
    List<AuthorDto> findByLastNameAllIgnoreCase(String lastName);
    List<AuthorDto> findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);
}
