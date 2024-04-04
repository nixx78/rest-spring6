package lv.nixx.poc.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;


@ToString
@Getter
public class NewPersonRequest {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Minimum allowed 'name' is '3'")
    private final String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 3, message = "Minimum allowed 'surname' is '3'")
    private final String surname;

    @NotNull
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