package lv.nixx.poc.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@ToString
@Getter
@Accessors(chain = true)
public class UpdatePersonRequest extends NewPersonRequest {

    @NotNull
    private final Long id;

    @JsonCreator
    public UpdatePersonRequest(
            Long id,
            String name,
            String surname,
            LocalDate dateOfBirth,
            String type
    ) {
        super(name, surname, dateOfBirth, type);
        this.id = id;
    }

}