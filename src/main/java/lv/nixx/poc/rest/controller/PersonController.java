package lv.nixx.poc.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lv.nixx.poc.rest.model.NewPersonRequest;
import lv.nixx.poc.rest.model.PersonDTO;
import lv.nixx.poc.rest.model.UpdatePersonRequest;
import lv.nixx.poc.rest.service.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Validated
public class PersonController {

    private final PersonDAO personService;

    @Autowired
    public PersonController(PersonDAO personService) {
        this.personService = personService;
    }

    @Operation(description = "Add new Person")
    @PostMapping("/person")
    public PersonDTO addPerson(@RequestBody @Valid NewPersonRequest request) {
        return personService.addPerson(request);
    }

    @Operation(description = "Update new Person")
    @PutMapping("/person")
    public PersonDTO updatePerson(@RequestBody @Valid UpdatePersonRequest request) {
        return personService.update(request);
    }

    @Operation(description = "Get list with all persons")
    @GetMapping("/person")
    public Collection<PersonDTO> getPersons() {
        return personService.getAllPersons();
    }

    @Operation(description = "Delete person")
    @DeleteMapping("/person")
    public PersonDTO deletePerson(@RequestParam @Valid @Min(value = 1, message = "Id must be greater than zero") Long id) {
        return personService.delete(id);
    }


}
