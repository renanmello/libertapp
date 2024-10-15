package ufpa.libertapp.contractrequest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufpa.libertapp.empresa.Empresa;
import ufpa.libertapp.orgao.Orgao;
import ufpa.libertapp.vitima.Vitima;

@Entity
@Table(name = "contractrequest")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Vitima vitima;

    @ManyToOne
    private Orgao orgao;

    private String status; // 'pending', 'approved', 'rejected'
}
