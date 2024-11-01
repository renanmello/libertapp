package ufpa.libertapp.empresa;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.orgao.Orgao;
import ufpa.libertapp.user.User;

/**
 * Entidade que representa uma empresa no sistema.
 * <p>
 * Contém informações sobre a empresa, como nome, CNPJ, e razão social, além de associações
 * com um usuário específico e um órgão regulador.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Entity
@Table(name = "empresa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
    /**
     * Identificador único da empresa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Usuário associado à empresa.
     * <p>
     * Ignorado durante a serialização JSON para evitar vazamento de informações sensíveis.
     * </p>
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
    /**
     * Órgão regulador associado à empresa.
     * <p>
     * Ignorado durante a serialização JSON para evitar carregamento excessivo de dados.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "orgao_id")  // Relacionamento com orgao, FK para a tabela orgao
    @JsonIgnore
    private Orgao orgao;
    /**
     * Nome da empresa.
     */
    private String nome;
    /**
     * Número de Cadastro Nacional da Pessoa Jurídica (CNPJ) da empresa.
     */
    private String cnpj;
    /**
     * Razão social da empresa.
     */
    private String razao_social;


}
