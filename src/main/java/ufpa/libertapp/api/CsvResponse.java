package ufpa.libertapp.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object (DTO) para representar a resposta ao processamento de arquivos CSV.
 * <p>
 * Esta classe encapsula a mensagem de status do processamento e, opcionalmente,
 * uma lista de mensagens de erro detalhadas caso ocorram falhas.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CsvResponse {
    /**
     * Mensagem de status descrevendo o resultado do processamento.
     */
    private String message;
    /**
     * Lista de mensagens de erro detalhadas, caso ocorram falhas no processamento do CSV.
     */
    private List<String> errors;
}
