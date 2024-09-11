package lv.nixx.poc.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lv.nixx.poc.rest.service.FileOperationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/files")
public class FileOperationsController {

    private final Path rootExchangeLocation = Paths.get(System.getProperty("user.dir")).resolve("exchange");

    private final FileOperationService fileOperationService;

    public FileOperationsController(FileOperationService fileOperationService) {
        this.fileOperationService = fileOperationService;
    }

    @Operation(summary = "Upload file",
            description = "Uploads a file to the server",
            responses = {
                    @ApiResponse(responseCode = "200", description = "File uploaded successfully"),
                    @ApiResponse(responseCode = "500", description = "Failed to upload file")
            })
    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") @Parameter(description = "File to upload", content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE))
            MultipartFile file) {

        try {
            fileOperationService.saveFile(file);

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
        try {

            byte[] fileContent = fileOperationService.getFileContent(filename);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", filename);

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage().getBytes());
        }
    }

    @GetMapping
    public Collection<FileOperationService.FileDTO> getAvailableFilesList() {
        return fileOperationService.getFilesList();
    }

    @GetMapping("/location")
    public String getExchangeLocation() {
        return fileOperationService.getExchangeLocation();
    }

}

