package lv.nixx.poc.rest.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lv.nixx.poc.rest.validation.person.PersonNameSurname;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Accessors(chain = true)
public class FindPersonsRequest extends RequestWithDateRange {
    @PersonNameSurname
    String name;
    @PersonNameSurname
    String surname;

}
