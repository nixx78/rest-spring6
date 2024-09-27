package lv.nixx.poc.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;
import lv.nixx.poc.rest.validation.person.PersonNameSurname;

import java.time.LocalDate;


@ToString
@Getter
public class NewPersonRequest {

    @NotBlank(message = "Name is mandatory")
    @PersonNameSurname
    private final String name;

    @NotBlank(message = "Surname is mandatory")
    @PersonNameSurname
    private final String surname;

    @NotNull(message = "Date of birth is mandatory")
    private final LocalDate dateOfBirth;

    @Pattern(regexp = "^[^|]*$", message = "Character '|' not allowed")
    private final String type;

    @JsonCreator
    public NewPersonRequest(
            String name,
            String surname,
            LocalDate dateOfBirth,
            String type
    ) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
    }

}