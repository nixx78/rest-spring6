package lv.nixx.poc.rest.controller;

import jakarta.validation.Valid;
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

    @PostMapping("/person")
    public PersonDTO addPerson(@RequestBody @Valid NewPersonRequest request) {
        return personService.addPerson(request);
    }

    @PutMapping("/person")
    public PersonDTO updatePerson(@RequestBody @Valid UpdatePersonRequest request) {
        return personService.update(request);
    }

    @GetMapping("/person")
    public Collection<PersonDTO> getPersons() {
        return personService.getAllPersons();
    }

    @DeleteMapping("/person")
    public PersonDTO deletePerson(Long id) {
        return personService.delete(id);
    }


}
