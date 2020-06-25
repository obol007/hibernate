package pl.obol.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Arrays;

@Getter @Setter
public class Student {
    private String firstName;
    private String lastName;
    private String about;
    private String password;
    private Language lang;
    private String[] skills;
    private String[] programs;
    @Column(nullable = false)
    private boolean terms;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "[" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", about='" + about + '\'' +
                ", password='" + password + '\'' +
                ", lang=" + lang + '\''+
                ", skills=" + Arrays.toString(skills) + '\''+
                ", programs=" + Arrays.toString(programs) + '\''+
                ", terms=" + terms +
                ']';
    }
}