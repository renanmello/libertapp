package ufpa.libertapp.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para operações relacionadas à entidade {@link Empresa}.
 * <p>
 * Fornece endpoints para visualizar, criar, atualizar e excluir empresas no sistema.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    private final EmpresaService empresaService;

    /**
     * Construtor para injeção de dependência do serviço de empresa.
     *
     * @param empresaService serviço que implementa a lógica de negócios para a entidade Empresa
     */
    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    /**
     * Visualiza os dados de uma empresa específica pelo ID.
     *
     * @param id o ID da empresa a ser visualizada
     * @return uma resposta contendo os dados da empresa
     */
    // Visualizar uma empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> view(@PathVariable Long id) {
        Empresa empresa = empresaService.view(id);
        return ResponseEntity.ok(empresa);
    }

    /**
     * Cria uma nova empresa associada a um usuário e um órgão específicos.
     *
     * @param userId  o ID do usuário associado à empresa
     * @param orgaoId o ID do órgão associado à empresa
     * @param empresa o objeto {@link Empresa} contendo os dados da nova empresa
     * @return uma resposta com o status de criação e os dados da empresa criada
     */
    // Criar uma nova empresa
    @PostMapping("/create/{userId}/{orgaoId}")
    public ResponseEntity<Empresa> create(
            @PathVariable Long userId,
            @PathVariable Long orgaoId,
            @RequestBody Empresa empresa) {
        Empresa createdEmpresa = empresaService.create(empresa, userId, orgaoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmpresa);
    }

    /**
     * Atualiza os dados de uma empresa existente.
     *
     * @param id      o ID da empresa a ser atualizada
     * @param empresa o objeto {@link Empresa} contendo os dados atualizados
     * @return uma resposta com os dados da empresa atualizada
     */
    // Atualizar uma empresa existente
    @PutMapping("/update/{id}")
    public ResponseEntity<Empresa> update(
            @PathVariable Long id,
            @RequestBody Empresa empresa) {
        Empresa updatedEmpresa = empresaService.update(empresa, id);
        return ResponseEntity.ok(updatedEmpresa);
    }

    /**
     * Exclui uma empresa específica pelo ID.
     *
     * @param id o ID da empresa a ser excluída
     * @return uma resposta sem conteúdo indicando sucesso na exclusão
     */
    // Deletar uma empresa por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
