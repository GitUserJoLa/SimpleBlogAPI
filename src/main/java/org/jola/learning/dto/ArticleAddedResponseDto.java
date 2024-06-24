package org.jola.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ArticleAddedResponseDto {
    private ArticleDto article;
    private String messageCode;
//    private HttpStatus httpStatus;
}
