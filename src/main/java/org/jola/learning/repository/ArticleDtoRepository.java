package org.jola.learning.repository;

import org.jola.learning.dto.ArticleDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleDtoRepository extends CrudRepository<ArticleDto, Long> {
    public ArticleDto findByTitle(String title);
    public List<ArticleDto> findByAuthorDtoAuthor(Long id);
}
