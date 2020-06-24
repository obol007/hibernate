package pl.obol.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Student {
    private String firstName;
    private String lastName;
    private String about;
    private String password;
    private Language lang;
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
                ", terms=" + terms +
                ']';
    }
}