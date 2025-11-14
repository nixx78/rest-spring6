package lv.nixx.poc.rest.service;

import lv.nixx.poc.rest.model.NewPersonRequest;
import lv.nixx.poc.rest.model.PersonDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class PersonCSVService {

    private static final Logger log = LoggerFactory.getLogger(PersonCSVService.class);

    private final CSVFormat csvFormatToRead;
    private final CSVFormat csvFormatToWrite;
    private final PersonService personService;

    public PersonCSVService(PersonService personService) {
        this.personService = personService;

        this.csvFormatToRead = CSVFormat.DEFAULT.builder()
                .setHeader("Name", "Surname", "DateOfBirth", "Type")
                .setSkipHeaderRecord(true)
                .build();

        this.csvFormatToWrite = CSVFormat.DEFAULT.builder()
                .setHeader("Id", "Name", "Surname", "DateOfBirth")
                .build();

    }

    public void saveFile(MultipartFile file) throws IOException {

        log.info("Start processing file [{}] size [{}]", file.getOriginalFilename(), file.getSize());

        CSVParser records = csvFormatToRead.parse(new StringReader(new String(file.getBytes())));

        List<NewPersonRequest> dtos = StreamSupport.stream(records.spliterator(), false)
                .map(t -> new NewPersonRequest(t.get("Name"), t.get("Surname"), LocalDate.parse(t.get("DateOfBirth")), t.get("Type")))
                .toList();

        dtos.forEach(personService::addPerson);
    }

    public byte[] getDataForDownload() throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormatToWrite)) {
            for (PersonDTO p : personService.getAllPersons()) {
                csvPrinter.printRecord(p.getId(), p.getName(), p.getSurname(), p.getDateOfBirth());
            }
        }

        return outputStream.toByteArray();
    }

}