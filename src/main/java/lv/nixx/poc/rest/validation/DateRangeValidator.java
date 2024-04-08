package lv.nixx.poc.rest.validation;

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

        return startDate != null && endDate != null && startDate.isBefore(endDate);
    }
}