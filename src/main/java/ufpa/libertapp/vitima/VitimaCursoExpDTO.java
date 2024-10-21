package ufpa.libertapp.vitima;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.curso.CursoDTO;
import ufpa.libertapp.experienciatrabalho.ExperienciaDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VitimaCursoExpDTO {
    private List<CursoDTO> cursos;
    private List<ExperienciaDTO> experiencias;
}
