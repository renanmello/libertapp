package ufpa.libertapp.curso;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operações relacionadas aos cursos.
 * <p>
 * Este controlador fornece endpoints para visualizar, criar e atualizar
 * cursos de um usuário específico.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    /**
     * Recupera a lista de cursos de um usuário específico.
     *
     * @param userId o ID do usuário para o qual os cursos serão recuperados
     * @return uma lista de cursos associados ao usuário
     */
    @GetMapping("/{user_id}")
    public List<Curso> view(@PathVariable("user_id") Long userId) {
        return cursoService.view(userId);
    }

    /**
     * Cria um novo curso associado a um usuário.
     *
     * @param curso  o objeto Curso a ser criado
     * @param userId o ID do usuário ao qual o curso será associado
     * @return o curso criado com uma resposta de status HTTP 200 (OK)
     */
    @PostMapping("create/{user_id}")
    public ResponseEntity<Curso> create(@RequestBody Curso curso, @PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(cursoService.create(curso, userId));
    }

    /**
     * Atualiza um curso existente de um usuário.
     *
     * @param curso   o objeto Curso com os dados atualizados
     * @param userId  o ID do usuário que possui o curso
     * @param cursoId o ID do curso a ser atualizado
     * @return o curso atualizado com uma resposta de status HTTP 200 (OK)
     */
    @PutMapping("update/{userId}/{cursoId}")
    public ResponseEntity<Curso> update(@RequestBody Curso curso, @PathVariable("userId") Long userId, @PathVariable("cursoId") Long cursoId) {
        return ResponseEntity.ok(cursoService.update(curso, userId, cursoId));

    }
}
