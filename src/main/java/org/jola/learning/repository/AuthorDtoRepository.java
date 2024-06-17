package org.jola.learning.repository;

import org.jola.learning.dto.AuthorDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorDtoRepository extends CrudRepository<AuthorDto, Long> {
    Optional<AuthorDto> findByAlias(String alias);
    List<AuthorDto> findByFirstName(String firstName);
    List<AuthorDto> findByLastName(String lastName);
    List<AuthorDto> findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);
}
