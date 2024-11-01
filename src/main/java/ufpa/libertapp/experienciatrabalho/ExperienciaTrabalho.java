package ufpa.libertapp.experienciatrabalho;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ufpa.libertapp.vitima.Vitima;

import java.time.LocalDate;

/**
 * Representa uma experiência de trabalho de uma vítima.
 * <p>
 * Cada experiência de trabalho inclui informações sobre a empresa, o cargo,
 * e as datas de início e fim da experiência.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Entity
@Table(name = "experiencia_trabalho")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciaTrabalho {
    /**
     * Identificador único da experiência de trabalho.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nome da empresa onde a vítima trabalhou.
     */
    private String nomeDaEmpresa;
    /**
     * Cargo ocupado pela vítima na empresa.
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

    /**
     * Vítima associada a esta experiência de trabalho.
     * <p>Define o relacionamento muitos-para-um com a entidade {@link Vitima}.</p>
     */
    @ManyToOne
    @JoinColumn(name = "vitima_cpf", referencedColumnName = "cpf")
    // Relacionamento com Vitima, FK para a tabela Vitima
    @JsonIgnore
    private Vitima vitima;

}
