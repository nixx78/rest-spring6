package lv.nixx.poc.rest.controller.person;

import lv.nixx.poc.rest.service.PersonCSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Validated
@RequestMapping("/person")
public class PersonFileOperationsController {

    private final PersonCSVService personCsvService;

    @Autowired
    public PersonFileOperationsController(PersonCSVService personCsvService) {
        this.personCsvService = personCsvService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        personCsvService.saveFile(file);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam(name = "filename", required = false) String filename) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", filename == null ? "persons.csv" : filename);

        return new ResponseEntity<>(personCsvService.getDataForDownload(), headers, HttpStatus.OK);
    }

}
