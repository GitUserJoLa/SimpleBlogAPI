package org.jola.learning.repository;

import org.jola.learning.dto.ArticleDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleDtoRepository extends CrudRepository<ArticleDto, Long> {

    List<ArticleDto> findByAuthorId(Long id);
    List<ArticleDto> findByAuthorAliasIgnoreCase(String alias);
}
