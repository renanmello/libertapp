package ufpa.libertapp.contractrequest;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositório para operações de banco de dados relacionadas à entidade {@link ContractRequest}.
 * <p>
 * Esta interface herda métodos padrão do JpaRepository para manipulação de dados da
 * entidade ContractRequest, incluindo operações de criação, leitura, atualização e exclusão.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface ContractRequestRepository extends JpaRepository<ContractRequest, Long> {}
