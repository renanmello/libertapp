package ufpa.libertapp.curso;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.vitima.Vitima;

/**
 * Representa um curso realizado por uma vítima.
 * <p>
 * Cada curso contém informações sobre a empresa fornecedora,
 * carga horária, conteúdo e a vítima associada.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Entity
@Table(name = "curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    /**
     * Identificador único do curso.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nome do curso.
     */
    private String nome;
    /**
     * Nome da empresa ou instituição que ofereceu o curso.
     */
    private String empresa_curso;
    /**
     * Carga horária do curso em horas.
     */
    private int horas;
    /**
     * Descrição ou conteúdo do curso.
     */
    private String conteudo;
    /**
     * Vítima que realizou o curso.
     * <p>Define o relacionamento muitos-para-um com a entidade {@link Vitima}.</p>
     */
    @ManyToOne
    @JoinColumn(name = "vitima_cpf")  // Relacionamento com Vitima, FK para a tabela Vitima
    @JsonIgnore
    private Vitima vitima;

}

