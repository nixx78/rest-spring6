package lv.nixx.poc.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lv.nixx.poc.rest.validation.person.DateRange;

import java.time.LocalDate;

@DateRange
@Data
public class RequestWithDateRange {
    @Schema(description = "From date (format: yyyy-MM-dd)", example = "2024-04-20")
    LocalDate from;
    @Schema(description = "To date (format: yyyy-MM-dd)", example = "2024-04-21")
    LocalDate to;

}
