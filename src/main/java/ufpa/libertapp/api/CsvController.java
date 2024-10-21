package ufpa.libertapp.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class CsvController {
    private final CsvService csvService;

    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        long maxSize = 1024L * 1024L * 1024L;
        if (file.getSize() > maxSize) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size exceeds the limit of 1 GB.");
        }
        if (file.isEmpty()) {
            return ResponseEntity.status(400).body("Please upload a valid CSV file.");
        }


        try {
            csvService.save(file); // Processa o arquivo CSV
            return ResponseEntity.ok("File uploaded and processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing CSV file: " + e.getMessage());
        }
    }
}
