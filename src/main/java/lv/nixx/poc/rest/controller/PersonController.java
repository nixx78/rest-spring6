package lv.nixx.poc.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lv.nixx.poc.rest.model.FindPersonsRequest;
import lv.nixx.poc.rest.model.NewPersonRequest;
import lv.nixx.poc.rest.model.PersonDTO;
import lv.nixx.poc.rest.model.UpdatePersonRequest;
import lv.nixx.poc.rest.service.CSVService;
import lv.nixx.poc.rest.service.PersonService;
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
import java.util.Map;

@RestController
@Validated
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final CSVService csvService;

    @Autowired
    public PersonController(PersonService personService, CSVService csvService) {
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
    public ResponseEntity<PersonDTO> addPerson(@RequestBody @Valid NewPersonRequest request) {
        PersonDTO savedPerson = personService.addPerson(request);

        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @Operation(description = "Add bulk of new Persons")
    @PostMapping("/bulk")
    public Collection<PersonDTO> addBulkPerson(@RequestBody @Valid Collection<NewPersonRequest> request) {
        return personService.addBulkPerson(request);
    }

    @Operation(description = "Update Person")
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
    public HttpStatus deletePerson(@RequestParam @Valid @Min(value = 1, message = "Id must be greater than zero") Long id) {
        personService.delete(id);

        return HttpStatus.NO_CONTENT;
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
    public String findAllByDetails(
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

    @PatchMapping("/{id}")
    @Operation(summary = "Partial entity update")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Update fields in entity",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = "object",
                            example = "{ \"name\": \"John\", \"surname\": \"Smith\", \"dateOfBirth\": \"2020-01-31\" }"
                    )
            )
    )
    // RFC 5789 — PATCH Method for HTTP
    public PersonDTO patchData(@PathVariable Long id, @RequestBody Map<PersonDTO.FieldsToPatch, Object> fieldsToPatch) {
        return personService.patchPerson(id, fieldsToPatch);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam(name = "filename", required = false) String filename) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", filename == null ? "persons.csv" : filename);

        return new ResponseEntity<>(csvService.getDataForDownload(), headers, HttpStatus.OK);
    }

}
