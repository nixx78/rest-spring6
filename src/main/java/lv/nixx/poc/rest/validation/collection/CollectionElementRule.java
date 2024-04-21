package lv.nixx.poc.rest.validation.collection;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CollectionElementValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionElementRule {
    String message() default "Collection elements not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String pattern() default "";

}
