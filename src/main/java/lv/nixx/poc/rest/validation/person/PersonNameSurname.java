package lv.nixx.poc.rest.validation.person;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})

@Size(min = 2, max = 50, message = "The person name/surname '${validatedValue}' must be between {min} and {max} characters long")
@Pattern(regexp = "^[A-Z][A-Za-z]+$")

public @interface PersonNameSurname {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}