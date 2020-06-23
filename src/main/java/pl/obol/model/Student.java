package pl.obol.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Student {
    private String firstName;
    private String lastName;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName +" " + lastName;
    }
}