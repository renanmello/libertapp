package ufpa.libertapp.experienciatrabalho;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ufpa.libertapp.vitima.Vitima;

import java.time.LocalDate;


@Entity
@Table(name = "experiencia_trabalho")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciaTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDaEmpresa;
    private String cargo;
    private LocalDate dataInicio;
    private LocalDate dataFim;


    @ManyToOne
    @JoinColumn(name = "vitima_cpf", referencedColumnName = "cpf")  // Relacionamento com Vitima, FK para a tabela Vitima
    @JsonBackReference
    private Vitima vitima;

}
