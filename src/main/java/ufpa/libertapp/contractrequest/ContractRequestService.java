package ufpa.libertapp.contractrequest;

/**
 * Serviço para operações relacionadas à entidade {@link ContractRequest}.
 * <p>
 * Define métodos para a criação e manipulação de solicitações de contrato entre empresa, órgão e vítima.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface ContractRequestService {
    /**
     * Cria uma nova solicitação de contrato.
     *
     * @return o objeto {@link ContractRequest} recém-criado
     */
    ContractRequest create();
}
