package lv.nixx.poc.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lv.nixx.poc.rest.validation.DateRange;

import java.time.LocalDate;

@Getter
@DateRange
public class FindPersonsRequest {

    @NotNull

    @Schema(description = "From date (format: yyyy-MM-dd)", example = "2024-04-20")
    private final LocalDate from;

    @Schema(description = "To date (format: yyyy-MM-dd)", example = "2024-04-21")
    @NotNull
    private final LocalDate to;

    @NotNull
    @Size(min = 2, max = 50, message = "The person name '${validatedValue}' must be between {min} and {max} characters long")
    @Pattern(regexp = "^[A-Z][A-Za-z]+$")
    private final String name;

    @NotNull
    @Size(min = 2, max = 50, message = "The person surname '${validatedValue}' must be between {min} and {max} characters long")
    @Pattern(regexp = "^[A-Z][A-Za-z]+$")
    private final String surname;

    @JsonCreator
    public FindPersonsRequest(
            @JsonProperty("from") LocalDate from,
            @JsonProperty("to") LocalDate to,
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname) {
        this.from = from;
        this.to = to;
        this.name = name;
        this.surname = surname;
    }
}
