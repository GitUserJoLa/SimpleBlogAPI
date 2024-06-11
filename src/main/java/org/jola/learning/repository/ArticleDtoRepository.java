package org.jola.learning.repository;

import org.jola.learning.dto.ArticleDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleDtoRepository extends CrudRepository<ArticleDto, Long> {
    public Optional<ArticleDto> findByTitle(String title);
    public List<ArticleDto> findByReadCount(Long readCount);
    public List<ArticleDto> findByHashTags(String[] hashTags);
    public List<ArticleDto> findByAuthorId(Long id);
    public List<ArticleDto> findByAuthorAlias(String alias);
    public List<ArticleDto> findByAuthorLastName(String lastName);
    public List<ArticleDto> findByAuthorFirstName(String firstName);

}
