package pl.obol.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import pl.obol.annotation.StartsWith;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.core.SpringProperties.getProperty;

@Entity
@Getter @Setter @ToString(exclude = "book")
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Transient
//    @Value("${startsWith.error.message}, B")
//    private String messageText;


    @Column(length = 20)
    @StartsWith(firstLetter = "B")
    @NotEmpty
    private String name;
    @NotBlank(message = "Pole nie może być puste")
    private String city;

    @OneToMany (mappedBy = "publisher")
    private List<Book> book = new ArrayList<>();


}
