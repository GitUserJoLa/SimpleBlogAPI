package org.jola.learning.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;

@Component
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "article")
public class ArticleDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Setter(AccessLevel.NONE)
    @Column(name = "article_id")
    private Long id;

//    As a rule of thumb, we should prefer the @NotNull annotation
//    over the @Column(nullable = false) annotation. This way, we make sure the validation takes place
//    before Hibernate sends any insert or update SQL queries to the database.
//    Also, it’s usually better to rely on the standard rules defined in the Bean Validation,
//    rather than letting the database handle the validation logic.
//    https://www.baeldung.com/hibernate-notnull-vs-nullable
    @NotNull
//    column constraint unique is based only on one field
    @Column(name = "article_title", unique = true)
    private String title;

    @Column(name = "article_description")
    private String description;

    @JoinColumn(name = "author_id")
//    @Column(name = "author_id")
    private AuthorDto author;

    @Column(name = "article_published")
    private boolean published;

    @Column(name = "article_read_count")
    private Long readCount;

    @Column(name = "article_reading_time")
    private int readingTime;

    @Column(name = "article_body")
    private String textBody;

    private Timestamp timeStamp;
    private String[] hashTags;
}
