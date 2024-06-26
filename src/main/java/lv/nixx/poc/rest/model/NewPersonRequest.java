package lv.nixx.poc.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @JsonCreator
    public NewPersonRequest(
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname,
            @JsonProperty("dateOfBirth") LocalDate dateOfBirth
    ) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

}