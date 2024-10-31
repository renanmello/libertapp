package ufpa.libertapp.curso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) para representar um curso.
 * <p>
 * Esta classe encapsula as informações de um curso, incluindo nome, empresa responsável,
 * duração em horas e conteúdo.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CursoDTO {
    /**
     * Identificador único do curso.
     */
    private Long id;
    /**
     * Nome do curso.
     */
    private String nome;
    /**
     * Nome da empresa que oferece o curso.
     */
    private String empresa_curso;
    /**
     * Duração do curso em horas.
     */
    private int horas;
    /**
     * Conteúdo abordado no curso.
     */
    private String conteudo;
}
