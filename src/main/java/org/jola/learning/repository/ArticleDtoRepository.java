package org.jola.learning.repository;

import org.jola.learning.dto.ArticleDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleDtoRepository extends CrudRepository<ArticleDto, Long> {
    Optional<ArticleDto> findByTitleAllIgnoreCase(String title);
    List<ArticleDto> findByReadCount(Long readCount);
//    List<ArticleDto> findByHashTags(String[] hashTags);
    List<ArticleDto> findByAuthorId(Long id);
    List<ArticleDto> findByAuthorAliasAllIgnoreCase(String alias);
    List<ArticleDto> findByAuthorLastNameAllIgnoreCase(String lastName);
    List<ArticleDto> findByAuthorFirstNameAllIgnoreCase(String firstName);
    List<ArticleDto> findByAuthorFirstNameAndAuthorLastNameAllIgnoreCase(String firstName, String lastName);

}
