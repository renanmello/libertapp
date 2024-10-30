package ufpa.libertapp.experienciatrabalho;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) para representar uma experiência de trabalho.
 * <p>
 * Esta classe é usada para transferir dados de uma experiência de trabalho
 * sem expor a entidade completa. Inclui informações sobre a empresa,
 * cargo e datas de início e fim da experiência.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExperienciaDTO {
    /**
     * Identificador único da experiência de trabalho.
     */
    private Long id;
    /**
     * Nome da empresa onde a experiência de trabalho ocorreu.
     */
    private String nomeDaEmpresa;
    /**
     * Cargo ocupado pelo usuário durante a experiência de trabalho.
     */
    private String cargo;
    /**
     * Data de início da experiência de trabalho.
     */
    private LocalDate dataInicio;
    /**
     * Data de término da experiência de trabalho.
     */
    private LocalDate dataFim;
}
