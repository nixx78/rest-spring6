package lv.nixx.poc.rest.validation.collection;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lv.nixx.poc.rest.model.RequestWithCollections;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

        RequestWithCollections r = new RequestWithCollections()
                .setCollectionWithCharacters(List.of("1", "ABC"))
                .setCollectionWithNumbers(List.of("X", "A", "1"));

        r.setFrom(LocalDate.parse("2024-01-01"));
        r.setTo(LocalDate.parse("2024-01-02"));

        List<String> violations = validator.validate(r)
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        assertThat(violations)
                .containsOnly("Collection elements not valid: [X,A] pattern [^[0-9]+$]", "Collection elements not valid: [1] pattern [^[A-Za-z]+$]");

    }

    @Test
    void validationSuccessTest() {
        Validator validator = factory.getValidator();

        RequestWithCollections requestWithCollections = new RequestWithCollections()
                .setCollectionWithCharacters(List.of("ABC"))
                .setCollectionWithNumbers(List.of("1"));

        requestWithCollections.setFrom(LocalDate.parse("2024-01-01"));
        requestWithCollections.setTo(LocalDate.parse("2024-01-02"));

        Set<ConstraintViolation<RequestWithCollections>> validate = validator.validate(requestWithCollections);
        assertEquals(0, validate.size());
    }

}
