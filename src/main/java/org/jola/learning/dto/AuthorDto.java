package org.jola.learning.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// "user" seems to be a reserved word so table must have another name
@Table(name = "author",
        uniqueConstraints = {
            // table constraint
            @UniqueConstraint(
                    name = "UniqueAuthoraliasAndPassword",
                    columnNames = {"author_alias", "author_email"}
            )
        }
    )
public class AuthorDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @NotNull
    @Column(name = "author_firstname")
    private String firstName;

    @NotNull
    @Column(name = "author_lastname")
    private String lastName;

    @NotNull
    @Column(name = "author_alias")
    private String alias;

    @NotNull
    @Column(name = "author_email")
    private String email;

    @NotNull
    @Column(name = "author_password")
    private String password;
}
