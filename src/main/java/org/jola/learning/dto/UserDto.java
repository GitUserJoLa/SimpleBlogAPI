package org.jola.learning.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table( name = "user",
        uniqueConstraints = {
            // table constraint
            @UniqueConstraint(
                    name = "UniqueUsernameAndPassword",
                    columnNames = {"user_alias", "user_email"}
            )
        }
    )
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column(name = "user_first_name")
    private String firstName;

    @NotNull
    @Column(name = "user_last_name")
    private String lastName;

    @NotNull
    @Column(name = "user_alias")
    private String alias;

    @NotNull
    @Column(name = "user_email")
    private String email;

    @NotNull
    @Column(name = "user_password")
    private String password;
}
