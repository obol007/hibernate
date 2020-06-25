package pl.obol.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @Setter @ToString(exclude = "books")
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Author(){}
    public Author(String name){
        this.name = name;
    }

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return name.equals(author.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
