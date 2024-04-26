package lv.nixx.poc.rest.validation.collection;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lv.nixx.poc.rest.model.RequestWithCollections;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CollectionElementValidatorTest {

    private static ValidatorFactory factory;

    @BeforeAll
    static void init() {
        factory = Validation.buildDefaultValidatorFactory();
    }

    @AfterAll
    static void cleanup() {
        factory.close();
    }

    @Test
    void validationFailTest() {

        Validator validator = factory.getValidator();

        RequestWithCollections findPersonsRequest = new RequestWithCollections(List.of("1", "ABC"), List.of("X", "A", "1"));

        List<String> violations = validator.validate(findPersonsRequest)
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertThat(violations)
                .containsOnly("Collection elements not valid: [X,A] pattern [^[0-9]+$]", "Collection elements not valid: [1] pattern [^[A-Za-z]+$]");

    }

    @Test
    void validationSuccessTest() {
        Validator validator = factory.getValidator();

        RequestWithCollections findPersonsRequest = new RequestWithCollections(List.of("ABC"), List.of("1"));

        assertEquals(0, validator.validate(findPersonsRequest).size());
    }

}
