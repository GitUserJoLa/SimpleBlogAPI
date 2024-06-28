package org.jola.learning.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Objects;

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
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(name = "firstname",
            nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "lastname",
            nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "alias",
            unique = true,
            nullable = false)
    private String alias;

    @NotNull
    @Column(name = "email",
            unique = true,
            nullable = false)
    @Setter(AccessLevel.NONE)
    private String email;

    @NotNull
    @Column(name = "password",
            nullable = false)
    @Getter(AccessLevel.NONE)
    // overhaul necessary. (SOLVED: pw should never be sent to the client on request!)
    // should also be stored hashed in db and never be clear text
    private String password;

    @Override
    public boolean equals(Object o) {

        if (o == this)
            return true;

        // When the entity identifier is null, equality is only guaranteed for the same object references.
        // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        // If we use the instanceof operator on any object that’s null, it returns false.
        // We also don’t need a null check when using an instanceof operator.
        // https://www.baeldung.com/java-instanceof
        if (this.getId() == null ||
                !(o instanceof AuthorDto))
            return false;
        AuthorDto author = (AuthorDto) o;
        return Objects.equals(author.getId(), this.getId());
    }

    @Override
    public int hashCode() {

        int result = 17;
        // this.id.hashCode() throws NPE at compile time
        // probably not null on run time -> needs validation
//        result = 31 * result + this.id.hashCode();

        // Objects.hash(varargs...)
        // The Objects class has a static method that takes an arbitrary number of objects and returns
        // a hash code for them. This method, named hash, lets you write one-line hashCode methods [...].
        // Unfortunately, they run more slowly because they entail array creation to pass a variable
        // number of arguments, as well as boxing and unboxing if any of the arguments are of primitive type.
        // This style of hash function is recommended for use only in situations where performance is not critical.
        // Joshua Bloch: Effective Java, p. 53; 3rd Edition, 2018
//        result = 31 * result + Objects.hash(this.getId());

        // Objects.hashCode(singleArg)
        // returns the hash code of a non-null argument and 0 for a null argument
        result = 31 * result + Objects.hashCode(this.getId());
        return result;
    } // getClass().hashCode() returns the same hashcode for all class instances
}
