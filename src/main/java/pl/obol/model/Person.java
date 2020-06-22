package pl.obol.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @Setter
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "people_phones",
            joinColumns=@JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "phone_id"))
    private List<Phone> phones;


}
