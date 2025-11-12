package lv.nixx.poc.rest.controller.person;

import lv.nixx.poc.rest.service.CSVService;
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

    private final CSVService csvService;

    @Autowired
    public PersonFileOperationsController(CSVService csvService) {
        this.csvService = csvService;
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
