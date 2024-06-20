package org.jola.learning.service;

import org.jola.learning.dto.ArticleDto;
import org.jola.learning.repository.ArticleDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDtoRepository articleDtoRepository;

    public List<ArticleDto> getAllArticlesByAuthorAlias(String alias) {
        return null;
    }
}
