package lv.nixx.poc.rest.validation.person;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lv.nixx.poc.rest.model.FindPersonsRequest;

import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<DateRange, FindPersonsRequest> {

    @Override
    public void initialize(DateRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(FindPersonsRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return false;
        }

        LocalDate startDate = request.getFrom();
        LocalDate endDate = request.getTo();

        boolean isValid = startDate != null && endDate != null && startDate.isBefore(endDate);

        if (isValid) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Date range [%s] - [%s]  not valid, end date must be greater that start date".formatted(startDate, endDate))
                    .addConstraintViolation();
            return false;
        }
    }
}