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
    void findPersonValidationTest() {

        assertAll(
                () -> ValidationTester.create()
                        .withExpectedViolations(
                                new ExpectedViolation(null, DATE_RANGE_MESSAGE),
                                new ExpectedViolation("name", MUST_NOT_BE_NULL),
                                new ExpectedViolation("surname", MUST_NOT_BE_NULL)
                        ).validate(new FindPersonsRequest(null, null, null, null)),
                () -> ValidationTester.create()
                        .withExpectedViolations(
                                new ExpectedViolation(null, DATE_RANGE_MESSAGE)
                        ).validate(new FindPersonsRequest(null, null, "Name", "Surname")),
                () -> ValidationTester.create()
                        .validate(new FindPersonsRequest(LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-10"), "Name", "Surname"))
        );

    }


}
