package ufpa.libertapp.vitima;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VitimaDTO {
    private String nome;
    private String email;
    private String telefone;
    private String password;
    private boolean contactada;

}
