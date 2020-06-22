package pl.obol.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @Setter
public class Phone {

    @Id
    @GeneratedValue
    private Long id;
    private String number;

    @ManyToMany(mappedBy = "phones")
    private List<Person> persons;
}
