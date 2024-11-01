package ufpa.libertapp.experienciatrabalho;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operações relacionadas à experiência de trabalho de um usuário.
 * <p>
 * Este controlador fornece endpoints para visualizar, criar e atualizar
 * experiências de trabalho associadas a um usuário específico.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@RestController
@RequestMapping("/experiencia")
@RequiredArgsConstructor
public class ExperienciaTrabalhoController {
    private final ExperienciaTrabalhoService experienciaTrabalhoService;

    /**
     * Recupera a lista de experiências de trabalho de um usuário específico.
     *
     * @param user_id o ID do usuário cujas experiências de trabalho serão recuperadas
     * @return uma lista de objetos {@link ExperienciaTrabalho} associados ao usuário
     */
    @GetMapping("/{user_id}")
    public List<ExperienciaTrabalho> view(@PathVariable("user_id") Long user_id) {
        return experienciaTrabalhoService.view(user_id);
    }

    /**
     * Cria uma nova experiência de trabalho para um usuário específico.
     *
     * @param experienciaTrabalho o objeto {@link ExperienciaTrabalho} a ser criado
     * @param user_id             o ID do usuário ao qual a experiência de trabalho será associada
     * @return a experiência de trabalho criada com uma resposta de status HTTP 200 (OK)
     */
    @PostMapping("create/{user_id}")
    public ResponseEntity<ExperienciaTrabalho> create(@RequestBody ExperienciaTrabalho experienciaTrabalho, @PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(experienciaTrabalhoService.create(experienciaTrabalho, user_id));
    }

    /**
     * Atualiza uma experiência de trabalho existente de um usuário.
     *
     * @param experienciaTrabalho o objeto {@link ExperienciaTrabalho} com os dados atualizados
     * @param user_id             o ID do usuário que possui a experiência de trabalho
     * @param exp_id              o ID da experiência de trabalho a ser atualizada
     * @return a experiência de trabalho atualizada com uma resposta de status HTTP 200 (OK)
     */
    @PutMapping("update/{user_id}/{exp_id}")
    public ResponseEntity<ExperienciaTrabalho> update(@RequestBody ExperienciaTrabalho experienciaTrabalho, @PathVariable("user_id") Long user_id, @PathVariable("exp_id") Long exp_id) {
        return ResponseEntity.ok(experienciaTrabalhoService.update(experienciaTrabalho, user_id, exp_id));
    }
}
