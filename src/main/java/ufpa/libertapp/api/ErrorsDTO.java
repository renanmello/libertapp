package ufpa.libertapp.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) para representar erros ao processar arquivos CSV.
 * <p>
 * Esta classe é usada para encapsular mensagens de erro detalhadas,
 * facilitando a resposta padronizada a erros na API.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorsDTO {
    /**
     * Mensagem descritiva do erro ocorrido durante o processamento.
     */
    private String memsage;
}
