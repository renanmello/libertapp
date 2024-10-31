package ufpa.libertapp.contractrequest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.empresa.Empresa;
import ufpa.libertapp.orgao.Orgao;
import ufpa.libertapp.vitima.Vitima;

/**
 * Entidade que representa uma solicitação de contrato entre uma empresa, órgão e vítima.
 * <p>
 * Cada solicitação contém informações sobre a empresa solicitante, a vítima do contrato,
 * o órgão responsável e o status da solicitação, que pode ser 'pending', 'approved', ou 'rejected'.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Entity
@Table(name = "contractrequest")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {
    /**
     * Identificador único da solicitação de contrato.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Empresa que faz a solicitação do contrato.
     */
    @ManyToOne
    private Empresa empresa;
    /**
     * Vítima que é o objeto da solicitação de contrato.
     */
    @ManyToOne
    private Vitima vitima;
    /**
     * Órgão responsável pelo contrato.
     */
    @ManyToOne
    private Orgao orgao;
    /**
     * Status da solicitação, indicando se está pendente, aprovada ou rejeitada.
     */
    private String status; // 'pending', 'approved', 'rejected'
}
