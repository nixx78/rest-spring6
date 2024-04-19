package lv.nixx.poc.rest.validation.collection;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lv.nixx.poc.rest.model.RequestWithCollection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CollectionElementValidatorTest {

    @Test
    void validationFailTest() {

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            RequestWithCollection findPersonsRequest = new RequestWithCollection(List.of("1", "ABC"), List.of("X", "A", "1"));

            List<String> violations = validator.validate(findPersonsRequest)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();

            assertThat(violations)
                    .containsOnly("Collection elements not valid: [X,A]", "Collection elements not valid: [1]");

        }
    }

    @Test
    void validationSuccessTest() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            RequestWithCollection findPersonsRequest = new RequestWithCollection(List.of("ABC"), List.of("1"));

            assertEquals(0, validator.validate(findPersonsRequest).size());
        }
    }

}
