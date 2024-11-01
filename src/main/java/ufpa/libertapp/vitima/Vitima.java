package ufpa.libertapp.vitima;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.curso.Curso;
import ufpa.libertapp.experienciatrabalho.ExperienciaTrabalho;
import ufpa.libertapp.user.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Entidade que representa uma vítima cadastrada no sistema.
 * <p>
 * Esta classe contém as informações pessoais da vítima, seu histórico de trabalho,
 * cursos e o currículo em formato PDF.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */

@Entity
@Table(name = "vitima")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vitima {

    /**
     * CPF da vítima, utilizado como identificador único.
     */
    @Id
    private String cpf;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
    /**
     * Nome completo da vítima.
     */
    private String nome;
    /**
     * Data de nascimento da vítima.
     */
    private LocalDate data_nascimento;
    /**
     * Endereço de email da vítima.
     */
    private String email;
    /**
     * Indicação se a vítima possui deficiência (PCD).
     */
    private String pcd;
    /**
     * Estado de residência da vítima.
     */
    private String estado;
    /**
     * Cidade de residência da vítima.
     */
    private String cidade;
    /**
     * CEP do endereço residencial da vítima.
     */
    private String cep;
    /**
     * Endereço residencial completo da vítima.
     */
    private String endereco;
    /**
     * Nível de escolaridade da vítima.
     */
    private String escolaridade;
    /**
     * RG da vítima.
     */
    private String rg;
    /**
     * Horário de disponibilidade da vítima para o trabalho.
     */
    private VitimaHorarios horario;
    /**
     * Telefone de contato da vítima.
     */
    private String telefone;
    /**
     * Lista de experiências de trabalho associadas à vítima.
     */
    @OneToMany(mappedBy = "vitima", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ExperienciaTrabalho> experiencias;
    /**
     * Lista de cursos realizados pela vítima.
     */
    @OneToMany(mappedBy = "vitima", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Curso> cursos;
    /**
     * Currículo da vítima em formato PDF, armazenado como um array de bytes.
     */
    @Lob
    private byte[] curriculoPdf;
    /**
     * Indica se a vítima confirmou o termo de uso.
     */
    private boolean confirmacao_termo;
    /**
     * Indica se a vítima está atualmente empregada.
     */
    private boolean empregada;
    /**
     * Indica se a vítima foi contactada para participar do programa.
     */
    private boolean contactada;

    /**
     * Verifica se a vítima confirmou o termo de uso.
     *
     * @return {@code true} se o termo foi confirmado, {@code false} caso contrário.
     */
    public boolean getConfirmacao_termo() {
        return this.confirmacao_termo;
    }
}
