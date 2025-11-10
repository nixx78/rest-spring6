package lv.nixx.poc.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Long personId;

    public PersonNotFoundException(Long personId) {
        this.personId = personId;
    }

    @Override
    public String getMessage() {
        return "Person with id [%s] not found".formatted(personId);
    }
}
