package ufpa.libertapp.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CsvController {
    private final CsvService csvService;

    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/upload")
    public ResponseEntity<CsvResponse> uploadCSV(@RequestParam("file") MultipartFile file) {
        long maxSize = 1024L * 1024L * 1024L;
        if (file.getSize() > maxSize) {
            CsvResponse response = new CsvResponse("File size exceeds the limit of 1 GB.", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (file.isEmpty()) {
            CsvResponse response = new CsvResponse("Please upload a valid CSV file.", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            List<String> errors = csvService.save(file); // Processa o arquivo CSV
            if (errors.isEmpty()) {
                CsvResponse response = new CsvResponse("File uploaded and processed successfully.", null);
                return ResponseEntity.ok(response);
            } else {
                System.out.println("Data processing error:");
                errors.forEach(System.out::println); // Imprime cada erro no console
                CsvResponse response = new CsvResponse("File processed with errors.", errors);
                return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(response); // Retorna os erros no objeto
            }
        } catch (Exception e) {
            CsvResponse response = new CsvResponse("Error processing CSV file: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}


