package org.jola.learning.repository;

import org.jola.learning.dto.AuthorDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorDtoRepository extends CrudRepository<AuthorDto, Long> {
    public Optional<AuthorDto> findByAlias(String alias);
    public List<AuthorDto> findByFirstName(String firstName);
    public List<AuthorDto> findByLastName(String lastName);
}
