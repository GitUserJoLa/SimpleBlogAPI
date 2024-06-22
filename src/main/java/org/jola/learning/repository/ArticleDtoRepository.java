package org.jola.learning.repository;

import org.jola.learning.dto.ArticleDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleDtoRepository extends CrudRepository<ArticleDto, Long> {
//    List<ArticleDto> findByTitleIgnoreCase(String title);
//    List<ArticleDto> findByReadCount(Long readCount);
//    List<ArticleDto> findByHashTags(String[] hashTags);
//    List<ArticleDto> findByAuthorAliasIgnoreCase(String alias);
//    List<ArticleDto> findByAuthorLastNameIgnoreCase(String lastName);
//    List<ArticleDto> findByAuthorFirstNameIgnoreCase(String firstName);
//    List<ArticleDto> findByAuthorFirstNameAndAuthorLastNameAllIgnoreCase(String firstName, String lastName);

    List<ArticleDto> findByAuthorId(Long id);
    List<ArticleDto> findByAuthorAliasIgnoreCase(String alias);

}
