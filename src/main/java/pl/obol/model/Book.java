package pl.obol.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    @Column(name = "book_title", nullable = false, length = 100)
    private String title;
    @Column(scale = 1, precision = 2)
    private BigDecimal rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "created_on")
    private LocalDateTime created;
    @Column(name = "updated_on")
    private LocalDateTime updated;

    @ManyToMany //(cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
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
