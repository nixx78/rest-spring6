package lv.nixx.poc.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lv.nixx.poc.rest.model.FindPersonsRequest;
import lv.nixx.poc.rest.model.NewPersonRequest;
import lv.nixx.poc.rest.model.PersonDTO;
import lv.nixx.poc.rest.model.UpdatePersonRequest;
import lv.nixx.poc.rest.service.CSVService;
import lv.nixx.poc.rest.service.PersonDAO;
import lv.nixx.poc.rest.validation.person.PersonNameSurname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@Validated
@RequestMapping("/person")
public class PersonController {

    private final PersonDAO personService;
    private final CSVService csvService;

    @Autowired
    public PersonController(PersonDAO personService, CSVService csvService) {
        this.personService = personService;
        this.csvService = csvService;
    }

    @Operation(description = "Get person by primary id")
    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable("id") @Min(1) Long id) {
        return personService.getById(id);
    }

    @Operation(description = "Add new Person")
    @PostMapping
    public PersonDTO addPerson(@RequestBody @Valid NewPersonRequest request) {
        return personService.addPerson(request);
    }

    @Operation(description = "Add new Person")
    @PostMapping("/bulk")
    public Collection<PersonDTO> addBulkPerson(@RequestBody @Valid Collection<NewPersonRequest> request) {
        return personService.addBulkPerson(request);
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
            @PathVariable(name = "name")
            @PersonNameSurname
            String name,
            @PathVariable(name = "surname")
            @PersonNameSurname
            String surname) {

        return "Processed:" + name + ":" + surname;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        csvService.saveFile(file);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam(name = "filename", required = false) String filename) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", filename == null ? "persons.csv" : filename);

        return new ResponseEntity<>(csvService.getDataForDownload(), headers, HttpStatus.OK);
    }

}
