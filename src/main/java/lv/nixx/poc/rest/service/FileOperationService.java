package lv.nixx.poc.rest.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class FileOperationService {

    private final Path rootExchangeLocation = Paths.get(System.getProperty("user.dir")).resolve("exchange");
    private final Logger log = LoggerFactory.getLogger(FileOperationService.class);

    public String getExchangeLocation() {
        return rootExchangeLocation.toString();
    }

    public void saveFile(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new IllegalArgumentException("File name can't be null");
        }

        if (!Files.exists(rootExchangeLocation)) {
            Files.createDirectories(rootExchangeLocation);
        }

        Path filePath = rootExchangeLocation.resolve(originalFilename);
        file.transferTo(filePath.toFile());
    }

    public byte[] getFileContent(String filename) throws IOException {
        Path filePath = rootExchangeLocation.resolve(filename);
        File file = filePath.toFile();

        if (!file.exists()) {
            throw new IllegalArgumentException("File [" + filename + "] not found");
        }

        byte[] fileContent = Files.readAllBytes(filePath);

        log.info("File for download, name [{}] size [{}]", filename, fileContent.length);

        return fileContent;
    }

    public Collection<FileDTO> getFilesList() {
        File file = rootExchangeLocation.toFile();
        File[] files = file.listFiles();

        if (files == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(files)
                .filter(File::isFile)
                .filter(f -> f.getName().endsWith(".txt"))
                .map(f -> {
                    Path filePath = f.toPath();
                    try {
                        BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
                        LocalDateTime creationTime = LocalDateTime.ofInstant(attrs.creationTime().toInstant(), ZoneId.systemDefault());
                        return new FileDTO(f.getName(), creationTime, attrs.size());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    @AllArgsConstructor
    @Getter
    public static class FileDTO {
        final String name;
        final LocalDateTime timestamp;
        final Long length;
    }

}
