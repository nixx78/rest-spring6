package lv.nixx.poc.rest.validation.collection;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.regex.Pattern;

public class CollectionElementValidator implements ConstraintValidator<CollectionElement, Collection<String>> {

    private Pattern pattern;

    @Override
    public void initialize(CollectionElement constraintAnnotation) {
        this.pattern = Pattern.compile(constraintAnnotation.pattern());
    }

    @Override
    public boolean isValid(Collection<String> collectionToValidate, ConstraintValidatorContext context) {
        if (collectionToValidate == null) {
            return false;
        }

        Collection<String> validationErrors = collectionToValidate.stream()
                .map(t -> pattern.matcher(t).matches() ? null : t
                ).filter(Objects::nonNull)
                .toList();

        if (CollectionUtils.isEmpty(validationErrors)) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(("Collection elements not valid: [" +
                            String.join(",", validationErrors) +
                            "]"))
                    .addConstraintViolation();

            return false;
        }
    }

}