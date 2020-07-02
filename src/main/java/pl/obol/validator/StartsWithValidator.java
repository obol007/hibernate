package pl.obol.validator;

import pl.obol.annotation.StartsWith;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartsWithValidator implements ConstraintValidator<StartsWith, String> {

    private String firstLetter;
    @Override
    public void initialize(StartsWith constraintAnnotation) {
    this.firstLetter = constraintAnnotation.firstLetter();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.startsWith(this.firstLetter);

    }
}
