package org.jola.learning.service;

import org.jola.learning.dto.ArticleDto;
import org.jola.learning.repository.ArticleDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    ArticleDtoRepository articleRepository;

    public List<ArticleDto> getAllArticles() {
        List<ArticleDto> articleList = new ArrayList<>();
        articleRepository.findAll()
                .forEach(
                        articleList::add
                );
        return articleList;

        // compiler warning: unchecked or unsafe operations
//        return new ArrayList<>((Collection) articleRepository.findAll());
    }

    public List<ArticleDto> getAllArticlesByAuthorId(Long id) {
        return new ArrayList<>(articleRepository.findByAuthorId(id));
    }

    public List<ArticleDto> getAllArticlesByAuthorAlias(String alias) {
        return new ArrayList<>(articleRepository.findByAuthorAliasIgnoreCase(alias));
    }

    public ArticleDto addArticle(ArticleDto article) {
        if (article.isPublished() && article.getTimestamp() == null)
            article.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return articleRepository.save(article);
    }

    public ArticleDto updateArticle(ArticleDto article) {
        // problem is receiving an incomplete json (controller) and turning this into an object with null values
        // existing values in db will be overwritten by null/default values
        Optional<ArticleDto> articlePulled = articleRepository.findById(article.getId());
        if (articlePulled.isEmpty())
            return addArticle(article);

        // no need to overwrite id, and also restricted
        if (article.getTitle() == null)
            article.setTitle(articlePulled.get().getTitle());
        if (article.getDescription() == null)
            article.setDescription(articlePulled.get().getDescription());
        // author - no setter, needs to be provided by client or will throw error
        // published - never null, needs to be provided by client
        if (article.getReadCount() == null)
            article.setReadCount(articlePulled.get().getReadCount());
        if (article.getReadingTime() == null)
            article.setReadCount(articlePulled.get().getReadCount());
        if (article.getContent() == null)
            article.setContent(articlePulled.get().getContent());
        if (article.getTimestamp() == null)
            article.setTimestamp(articlePulled.get().getTimestamp());
        if (article.getHashTags() == null)
            article.setHashTags(articlePulled.get().getHashTags());

        return addArticle(article);
    }

    public String deleteArticle(ArticleDto article) {
        articleRepository.delete(article);
        return "Article successfully deleted";
    }
}
