package pl.obol.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "books")
@ToString(exclude = {"created", "updated"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "book_title", nullable = false, length = 100)
    private String title;
    @Column(scale = 1, precision = 2)
    private BigDecimal rating;
    private String author;
    @Column(name = "created_on")
    private LocalDateTime created;
    @Column(name = "updated_on")
    private LocalDateTime updated;

    @PrePersist
    public void setDateCreated(){
        created = LocalDateTime.now();
    }
    @PreUpdate
    public void setDateUpdated(){
        updated = LocalDateTime.now();
    }


}
