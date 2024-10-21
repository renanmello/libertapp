package ufpa.libertapp.curso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CursoDTO {

    private String nome;
    private String empresa_curso;
    private int horas;
    private String conteudo;
}
