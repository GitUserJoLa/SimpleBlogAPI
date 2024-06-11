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
@Table(name = "author")
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
    @Column(name = "author_alias",
            unique = true)
    private String alias;

    @NotNull
    @Column(name = "author_email",
            unique = true)
    private String email;

    @NotNull
    @Column(name = "author_password")
    private String password;
}
