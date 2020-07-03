package pl.obol.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.obol.validator.BookValidation;
import pl.obol.validator.PropositionValidation;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "books")
@ToString //(exclude = {"created", "updated"})
public class Book {

    @Transient
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //definicja tabeli
    @Column(name = "book_title", nullable = false, length = 100)
    //walidacja
    @Size(min = 2, max = 40, groups = {PropositionValidation.class, BookValidation.class})
    private String title;

    private Boolean proposition = false;

    //definicja tabeli
    @Column(scale = 1, precision = 3)
    //walidacja
    @DecimalMax("10")
    @DecimalMin("0")
    @NotNull(groups = {BookValidation.class})
    private BigDecimal rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    @NotNull(groups = {BookValidation.class})
    private Publisher publisher;

    @Column(name = "created_on")
    private LocalDateTime created;
    @Column(name = "updated_on")
    private LocalDateTime updated;

    @ManyToMany //(cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @NotNull(groups = {BookValidation.class})
    private List<Author> authors;

    @PrePersist
    public void setDateCreated(){
        String date = formatter.format(LocalDateTime.now());
        created = LocalDateTime.parse(date,formatter);
    }
    @PreUpdate
    public void setDateUpdated(){
        String date = formatter.format(LocalDateTime.now());
        updated = LocalDateTime.parse(date,formatter);
    }

    public void addAuthor(Author a){
        this.authors.add(a);
    }



}
