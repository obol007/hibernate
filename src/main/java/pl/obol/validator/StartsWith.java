package pl.obol.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StartsWithValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StartsWith {

    //parametr adnotacji - obowiązkowy. Jeżeli jest tylko jeden, to możemy nazwać go value
    // i nie deklarować go przy adnotacji, tylko podać samą nazwę, np.
//    String value();

    String firstLetter();

    String message() default "{startsWith.error.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
