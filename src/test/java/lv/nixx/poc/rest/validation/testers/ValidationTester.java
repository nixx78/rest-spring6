package lv.nixx.poc.rest.validation.testers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidationTester {

    private Collection<ExpectedViolation> expectedViolations;

    public static ValidationTester create() {
        return new ValidationTester();
    }

    public ValidationTester withExpectedViolations(ExpectedViolation... expectedResults) {
        this.expectedViolations = Stream.of(expectedResults).toList();
        return this;
    }

    public void validate(Object classToTest) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Object>> validate = validator.validate(classToTest);

            List<ExpectedViolation> actualResult = validate.stream()
                    .map(t -> {
                        String propertyPath = t.getPropertyPath().toString();
                        return new ExpectedViolation(StringUtils.isEmpty(propertyPath) ? null : propertyPath, t.getMessage());
                    })
                    .toList();

            if (CollectionUtils.isEmpty(expectedViolations)) {
                assertTrue(CollectionUtils.isEmpty(actualResult), "Expected success validation, no violations");
            } else {
                assertThat(actualResult)
                        .usingRecursiveComparison()
                        .ignoringCollectionOrder()
                        .isEqualTo(expectedViolations);
            }
        }
    }

    public record ExpectedViolation(String field, String message) {
    }

}
