package org.jola.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleAddedResponseDto {
    private ArticleDto article;
    private String message;
}
