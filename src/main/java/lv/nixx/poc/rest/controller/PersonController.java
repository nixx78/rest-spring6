package lv.nixx.poc.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lv.nixx.poc.rest.model.FindPersonsRequest;
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
@RequestMapping("/person")
public class PersonController {

    private final PersonDAO personService;

    @Autowired
    public PersonController(PersonDAO personService) {
        this.personService = personService;
    }

    @Operation(description = "Add new Person")
    @PostMapping
    public PersonDTO addPerson(@RequestBody @Valid NewPersonRequest request) {
        return personService.addPerson(request);
    }

    @Operation(description = "Update new Person")
    @PutMapping
    public PersonDTO updatePerson(@RequestBody @Valid UpdatePersonRequest request) {
        return personService.update(request);
    }

    @Operation(description = "Get list with all persons")
    @GetMapping
    public Collection<PersonDTO> getPersons() {
        return personService.getAllPersons();
    }

    @Operation(description = "Delete person")
    @DeleteMapping
    public PersonDTO deletePerson(@RequestParam @Valid @Min(value = 1, message = "Id must be greater than zero") Long id) {
        return personService.delete(id);
    }

    @Operation(description = "Find persons by parameters")
    @PostMapping("/find")
    public Collection<PersonDTO> findPersons(@RequestBody @Valid FindPersonsRequest findPersonsRequest) {
        return null;
    }

    @Operation(description = "Find persons by collection of parameters")
    @PostMapping("/findAll")
    public Collection<PersonDTO> findAllPersons(@RequestBody @Valid Collection<FindPersonsRequest> findPersonsRequest) {
        return null;
    }

    @Operation(description = "Find persons by name and surname")
    @GetMapping("/findAll/{name}/{surname}")
    public String findAllBySurname(
            @Valid
            @PathVariable(name = "name")
            @Size(min = 2, max = 50, message = "The person name '${validatedValue}' must be between {min} and {max} characters long")
            @Pattern(regexp = "^[A-Z][A-Za-z]+$")
            String name,
            @Valid
            @PathVariable(name = "surname")
            @Size(min = 2, max = 50, message = "The person surname '${validatedValue}' must be between {min} and {max} characters long")
            @Pattern(regexp = "^[A-Z][A-Za-z]+$")
            String surname) {

        return "Processed:" + name + ":" + surname;
    }


}
