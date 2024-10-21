package ufpa.libertapp.experienciatrabalho;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExperienciaDTO {
    private Long id;
    private String nomeDaEmpresa;
    private String cargo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
