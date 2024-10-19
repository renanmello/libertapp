package ufpa.libertapp.orgao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.empresa.Empresa;
import ufpa.libertapp.user.User;

import java.util.List;

@Entity
@Table(name = "orgao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orgao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonBackReference
    private User user;

    private String nome;

    @OneToMany(mappedBy = "orgao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Empresa> empresas_cad; //lista das empresas cadastradas pelo orgao
}
