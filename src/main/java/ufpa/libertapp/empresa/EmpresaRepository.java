package ufpa.libertapp.empresa;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para operações de banco de dados relacionadas à entidade {@link Empresa}.
 * <p>
 * Esta interface herda métodos padrão do {@link JpaRepository} para manipulação de dados da
 * entidade Empresa, incluindo operações de criação, leitura, atualização e exclusão.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
