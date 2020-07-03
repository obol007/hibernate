package pl.obol.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.obol.validator.StartsWith;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString(exclude = "book")
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20)
    @StartsWith(firstLetter = "B")
    private String name;
    @NotBlank(message = "Pole nie może być puste")
    private String city;

    @OneToMany (mappedBy = "publisher")
    private List<Book> book = new ArrayList<>();


}
