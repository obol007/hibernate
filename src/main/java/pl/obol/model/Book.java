package pl.obol.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String author;

    @ManyToOne
//            (cascade = CascadeType.REMOVE)
    @JoinColumn(name = "publisher")
    private Publisher publisher;

    @Column(name = "created_on")
    private LocalDateTime created;
    @Column(name = "updated_on")
    private LocalDateTime updated;

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


}
