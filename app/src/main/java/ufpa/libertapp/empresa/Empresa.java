package ufpa.libertapp.empresa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.orgao.Orgao;
import ufpa.libertapp.user.User;
import ufpa.libertapp.vitima.Vitima;

@Entity
@Table(name = "empresa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "orgao_id")  // Relacionamento com orgao, FK para a tabela orgao
    @JsonIgnore
    private Orgao orgao;

    private String nome;
    private String cnpj;
    private String razao_social;




}
