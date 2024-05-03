package lv.nixx.poc.rest.validation;

import lv.nixx.poc.rest.model.FindPersonsRequest;
import lv.nixx.poc.rest.validation.testers.ValidationTester;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static lv.nixx.poc.rest.validation.testers.ValidationTester.ExpectedViolation;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FindPersonRequestValidationTest {

    private static final String MUST_NOT_BE_NULL = "must not be null";
    private static final String DATE_RANGE_MESSAGE = "Date range [null] - [null]  not valid, end date must be greater that start date";

    @Test
    void validationFailTest() {

        assertAll(
                () -> ValidationTester.create()
                        .withExpectedViolations(
                                new ExpectedViolation(null, DATE_RANGE_MESSAGE),
                                new ExpectedViolation("name", MUST_NOT_BE_NULL),
                                new ExpectedViolation("surname", MUST_NOT_BE_NULL)
                        ).validate(new FindPersonsRequest()),

                () -> ValidationTester.create()
                        .withExpectedViolations(
                                new ExpectedViolation(null, DATE_RANGE_MESSAGE)
                        ).validate(new FindPersonsRequest()
                                .setName("Name")
                                .setSurname("Surname")
                        )
        );

    }

    @Test
    void validationSuccessTest() {

        FindPersonsRequest r = new FindPersonsRequest()
                .setName("Name")
                .setSurname("Surname");

        r.setFrom(LocalDate.parse("2024-01-01"));
        r.setTo(LocalDate.parse("2024-01-10"));

        ValidationTester.create().validate(r);
    }


}
