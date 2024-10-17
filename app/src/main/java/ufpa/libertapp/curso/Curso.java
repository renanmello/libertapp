package ufpa.libertapp.curso;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.vitima.Vitima;

@Entity
@Table(name = "curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String empresa_curso;
    private int horas;
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "vitima_cpf")  // Relacionamento com Vitima, FK para a tabela Vitima
    @JsonIgnore
    private Vitima vitima;

}

