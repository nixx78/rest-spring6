package lv.nixx.poc.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Person") 
public class PersonNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(String message) {
		super(message);
	}


}
