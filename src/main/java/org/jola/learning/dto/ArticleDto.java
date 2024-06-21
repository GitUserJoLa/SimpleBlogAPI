package org.jola.learning.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

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
    @Column(name = "id")
    private Long id;

    //    As a rule of thumb, we should prefer the @NotNull annotation
//    over the @Column(nullable = false) annotation. This way, we make sure the validation takes place
//    before Hibernate sends any insert or update SQL queries to the database.
//    Also, it’s usually better to rely on the standard rules defined in the Bean Validation,
//    rather than letting the database handle the validation logic.
//    https://www.baeldung.com/hibernate-notnull-vs-nullable
    @NotNull
//    column constraint unique is based only on one field
    @Column(name = "title",
            unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "author_id")
    @ManyToOne
    @NotNull
    private AuthorDto author;

    @Column(name = "published")
    private boolean published;

    @Column(name = "read_count")
    private Long readCount;

    @Column(name = "reading_time")
    private int readingTime;

    //    Using the @Lob annotation on the description field, we instruct Hibernate to manage this field
//    using the PostgreSQL TEXT type.
//    Note that when we use Hibernate with PostgreSQL, the storage mechanics become unusual when handling
//    a String attribute annotated with @Lob. [...]
//    For instance, PostgreSQL stores the contents of a column annotated with @Lob in a separate table,
//    the column itself will only store a UID for each entry in that table. Therefore, this behaviour
//    may lead to information loss. To solve this problem, we can either use @Column(columnDefinition="TEXT")
//    along with the @Lob annotation or use only @Column(columnDefinition = "TEXT").
//    https://www.baeldung.com/jpa-annotation-postgresql-text-type
    @Column(name = "content",
            columnDefinition = "TEXT")
    private String textBody;

    private Timestamp timeStamp;
    private String[] hashTags;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ArticleDto))
            return false;

        ArticleDto article = (ArticleDto) o;

        return this.getId() != null &&
                Objects.equals(article.getId(), this.getId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Objects.hashCode(this.getId());
        return result;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author.toString() + '\'' +
                ", published=" + published + '\'' +
                ", readCount=" + readCount + '\'' +
                ", readingTime=" + readingTime + '\'' +
                ", textBody=" + textBody + '\'' +
                ", timeStamp=" + timeStamp + '\'' +
                ", hashTags=" + Arrays.toString(hashTags) +
                '}';
    }
}
