package lv.nixx.poc.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lv.nixx.poc.rest.validation.person.DateRange;
import lv.nixx.poc.rest.validation.person.PersonNameSurname;

import java.time.LocalDate;

@DateRange
public record FindPersonsRequest(
        @Schema(description = "From date (format: yyyy-MM-dd)", example = "2024-04-20") LocalDate from,
        @Schema(description = "To date (format: yyyy-MM-dd)", example = "2024-04-21") LocalDate to,
        @PersonNameSurname String name,
        @PersonNameSurname String surname
) {
}
