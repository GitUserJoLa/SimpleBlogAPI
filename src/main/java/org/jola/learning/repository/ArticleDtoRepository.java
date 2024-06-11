package org.jola.learning.repository;

import org.jola.learning.dto.ArticleDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleDtoRepository extends CrudRepository<ArticleDto, Long> {
    Optional<ArticleDto> findByTitle(String title);
    List<ArticleDto> findByReadCount(Long readCount);
    List<ArticleDto> findByHashTags(String[] hashTags);
    List<ArticleDto> findByAuthorId(Long id);
    List<ArticleDto> findByAuthorAlias(String alias);
    List<ArticleDto> findByAuthorLastName(String lastName);
    List<ArticleDto> findByAuthorFirstName(String firstName);
}
