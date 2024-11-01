package ufpa.libertapp.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controlador REST para operações de upload e processamento de arquivos CSV.
 * <p>
 * O controlador permite o envio de arquivos CSV para o sistema,
 * onde são processados e armazenados, além de verificar erros no arquivo.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@RestController
@RequestMapping("/api")
public class CsvController {
    private final CsvService csvService;

    /**
     * Construtor para injeção de dependência do serviço CSV.
     *
     * @param csvService serviço para processamento de arquivos CSV
     */
    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

    /**
     * Endpoint para upload de um arquivo CSV.
     * <p>
     * Este endpoint recebe um arquivo CSV, verifica seu tamanho e se está vazio,
     * e então chama o serviço para processar e armazenar os dados.
     * Retorna uma resposta indicando sucesso, falhas de processamento ou erro no tamanho.
     * </p>
     *
     * @param file o arquivo CSV enviado para upload
     * @return {@link ResponseEntity} contendo uma mensagem de sucesso ou erro e uma lista de erros, se houver
     */
    @PostMapping("/upload")
    public ResponseEntity<CsvResponse> uploadCSV(@RequestParam("file") MultipartFile file) {
        long maxSize = 1024L * 1024L * 1024L;
        // Verifica se o arquivo excede o tamanho máximo permitido
        if (file.getSize() > maxSize) {
            CsvResponse response = new CsvResponse("File size exceeds the limit of 1 GB.", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        // Verifica se o arquivo está vazio
        if (file.isEmpty()) {
            CsvResponse response = new CsvResponse("Please upload a valid CSV file.", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            // Processa o arquivo CSV e captura erros, se houver
            List<String> errors = csvService.save(file);
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
