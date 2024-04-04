package lv.nixx.poc.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Getter
@Accessors(chain = true)
public class UpdatePersonRequest extends NewPersonRequest {

    @NotNull
    private final Long id;

    @JsonCreator
    public UpdatePersonRequest(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname,
            @JsonProperty("dateOfBirth") LocalDate dateOfBirth
    ) {
        super(name, surname, dateOfBirth);
        this.id = id;
    }

}