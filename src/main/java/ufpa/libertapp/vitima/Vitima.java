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

@Entity
@Table(name = "vitima")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vitima {
    @Id
    private String cpf;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;

    private String nome;

    private LocalDate data_nascimento;

    private String email;

    private String pcd;

    private String estado;

    private String cidade;

    private String cep;

    private String endereco;

    private String escolaridade;

    private String rg;

    private VitimaHorarios horario;

    private String telefone;

    @OneToMany(mappedBy = "vitima", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ExperienciaTrabalho> experiencias;

    @OneToMany(mappedBy = "vitima", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Curso> cursos;

    @Lob
    private byte[] curriculoPdf;

    private boolean confirmacao_termo;

    private boolean empregada;

    private boolean contactada;

    public boolean getConfirmacao_termo() {
        return this.confirmacao_termo;
    }
}
