package ufpa.libertapp.vitima;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.experienciatrabalho.ExperienciaTrabalho;
import ufpa.libertapp.user.User;

import java.util.Date;
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
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private String nome;

    private Date data_nascimento;

    private String email;

    private String pcd;

    private String estado;

    private String cidade;

    private String cep;

    private String endereco;

    private String escolaridade;

    @OneToMany(mappedBy = "vitima", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ExperienciaTrabalho> experiencias;

    @Lob
    private byte[] curriculoPdf;

    private boolean confirmacao_termo;

    private boolean empregada;


    public boolean getConfirmacao_termo() {
        return this.confirmacao_termo;
    }
}
