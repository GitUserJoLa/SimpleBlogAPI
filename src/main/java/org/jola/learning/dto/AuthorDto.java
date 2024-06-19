package org.jola.learning.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    // overhaul necessary. pw should never be sent to the client on request!
    // should also be stored hashed in db and never be clear text
    private String password;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AuthorDto))
            return false;

        AuthorDto author = (AuthorDto) o;

        // When the entity identifier is null, equality is only guaranteed for the same object references.
        // Otherwise, no transient object is equal to any other transient or persisted object.
        // That’s why the identifier equality check is done only if the current Object identifier is not null.
        // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return this.getId() != null &&
                Objects.equals(author.getId(), this.getId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        // this.id.hashCode() throws NPE
//        result = 31 * result + this.id.hashCode();

        // doesn't throw NPE at compile time
        // hash(varargs...)
        // The Objects class has a static method that takes an arbitrary number of objects and returns
        // a hash code for them. This method, named hash, lets you write one-line hashCode methods [...].
        // Unfortunately, they run more slowly because they entail array creation to pass a variable
        // number of arguments, as well as boxing and unboxing if any of the arguments are of primitive type.
        // This style of hash function is recommended for use only in situations where performance is not critical.
        // Joshua Bloch: Effective Java, p. 53; 3rd Edition, 2018
//        result = 31 * result + Objects.hash(this.getId());

        // doesn't throw NPE at compile time
        // hashCode(singlearg)
        result = 31 * result + Objects.hashCode(this.getId());

        return result;
    } // getClass().hashCode() returns the same hashcode for all class instances
}
