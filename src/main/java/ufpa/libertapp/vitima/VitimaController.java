package ufpa.libertapp.vitima;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufpa.libertapp.curso.CursoDTO;
import ufpa.libertapp.experienciatrabalho.ExperienciaDTO;

import java.util.List;

/**
 * Controlador REST para operações relacionadas às vítimas.
 * <p>
 * Este controlador fornece endpoints para visualizar, criar, atualizar e deletar
 * dados de vítimas, além de listar cursos e experiências de trabalho associados.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@RestController
@RequestMapping("/vitima")
@RequiredArgsConstructor
public class VitimaController {

    private final VitimaService vitimaService;

    /**
     * Recupera os dados de uma vítima específica pelo ID do usuário.
     *
     * @param user_id o ID do usuário associado à vítima
     * @return o objeto {@link Vitima} com os dados da vítima
     */
    @GetMapping("/get/{user_id}")
    public Vitima viewDados(@PathVariable("user_id") Long user_id) {
        return vitimaService.viewDados(user_id);
    }

    /**
     * Recupera todos os registros de vítimas.
     *
     * @return uma lista de objetos {@link Vitima}
     */
    @GetMapping("/all")
    public List<Vitima> viewAll() {
        return vitimaService.viewAll();
    }

    /**
     * Recupera detalhes de todas as vítimas no formato {@link VitimaDTO}.
     *
     * @return uma lista de {@link VitimaDTO} com detalhes das vítimas
     */
    @GetMapping("/details")
    public ResponseEntity<List<VitimaDTO>> getAllVitimasDetails() {
        List<VitimaDTO> vitimaDetails = vitimaService.findAllVitimaDetails();
        return ResponseEntity.ok(vitimaDetails);
    }

    /**
     * Cria um novo registro de vítima associado a um usuário.
     *
     * @param vitima  o objeto {@link Vitima} com os dados da vítima
     * @param user_id o ID do usuário associado à vítima
     * @return o objeto {@link Vitima} recém-criado com status HTTP 200 (OK)
     */
    @PostMapping("/{user_id}")
    public ResponseEntity<Vitima> create(@RequestBody Vitima vitima, @PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(vitimaService.create(vitima, user_id));
    }

    /**
     * Atualiza os dados de uma vítima específica associada a um usuário.
     *
     * @param vitima  o objeto {@link Vitima} com os dados atualizados da vítima
     * @param user_id o ID do usuário associado à vítima
     * @return o objeto {@link Vitima} atualizado com status HTTP 200 (OK)
     */
    @PutMapping("update/{user_id}")
    public ResponseEntity<Vitima> update(@RequestBody Vitima vitima, @PathVariable("user_id") Long user_id) {
        Vitima updatedVitima = vitimaService.update(vitima, user_id);
        return ResponseEntity.ok(updatedVitima);
    }

    /**
     * Recupera cursos e experiências de trabalho associados a uma vítima.
     *
     * @param userId o ID do usuário associado à vítima
     * @return um objeto {@link VitimaCursoExpDTO} contendo listas de {@link CursoDTO} e {@link ExperienciaDTO}
     */
    @GetMapping("/{userId}/cursos-exp")
    public ResponseEntity<VitimaCursoExpDTO> getCursosEExperiencias(@PathVariable Long userId) {
        List<CursoDTO> cursos = vitimaService.findCursosByUserId(userId);
        List<ExperienciaDTO> experiencias = vitimaService.findExperienciasByUserId(userId);

        VitimaCursoExpDTO vitimaCursoExpDTO = new VitimaCursoExpDTO(cursos, experiencias);

        return ResponseEntity.ok(vitimaCursoExpDTO);
    }

    /**
     * Deleta o registro de uma vítima pelo CPF.
     *
     * @param cpf o CPF da vítima a ser deletada
     */
    @DeleteMapping
    public void delete(@RequestParam("cpf") String cpf) {
        vitimaService.delete(cpf);
    }
}
