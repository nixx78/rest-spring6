package lv.nixx.poc.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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

    @JsonCreator
    public FindPersonsRequest(@JsonProperty("from") LocalDate from, @JsonProperty("to") LocalDate to) {
        this.from = from;
        this.to = to;
    }
}
