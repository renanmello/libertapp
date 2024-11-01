package ufpa.libertapp.vitima;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.curso.CursoDTO;
import ufpa.libertapp.experienciatrabalho.ExperienciaDTO;

import java.util.List;

/**
 * Data Transfer Object (DTO) para encapsular as informações de cursos e experiências
 * de trabalho associadas a uma vítima.
 * <p>
 * Esta classe contém listas de cursos e experiências de trabalho, facilitando o
 * transporte desses dados de forma agrupada.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VitimaCursoExpDTO {
    /**
     * Lista de cursos associados à vítima.
     */
    private List<CursoDTO> cursos;
    /**
     * Lista de experiências de trabalho associadas à vítima.
     */
    private List<ExperienciaDTO> experiencias;
}
