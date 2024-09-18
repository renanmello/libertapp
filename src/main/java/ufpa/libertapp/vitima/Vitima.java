package ufpa.libertapp.vitima;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.user.User;

import java.util.Date;

@Entity
@Table(name = "vitima")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vitima  {



    @Id
    private String cpf;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String nome;

    private Date data_nascimento;

    private String email;

    private String pcd;

    private String estado;

    private String cidade;

    private String cep;

    private String endereco;

    @Lob
    private byte[] curriculoPdf;

    private boolean confirmacao_termo;

    private boolean empregada;


    public boolean getConfirmacao_termo() {
        return this.confirmacao_termo;
    }
}
