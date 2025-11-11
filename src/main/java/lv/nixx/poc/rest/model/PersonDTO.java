package lv.nixx.poc.rest.model;

import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@Setter
public class PersonDTO {

    private long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    public enum FieldsToPatch {
        name, surname, dateOfBirth;
    }

}