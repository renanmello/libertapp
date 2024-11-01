package ufpa.libertapp.curso;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ufpa.libertapp.vitima.Vitima;
import ufpa.libertapp.vitima.VitimaRepository;

import java.util.List;

/**
 * Implementação do serviço de cursos, responsável por operações
 * de visualização, criação e atualização de cursos associados a usuários.
 *
 * @version 2.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
    private final VitimaRepository vitimaRepository;
    private final CursoRepository cursoRepository;

    /**
     * Recupera todos os cursos associados ao usuário com o ID especificado.
     *
     * @param userId o ID do usuário cujos cursos serão recuperados
     * @return uma lista de cursos associados ao usuário
     */
    @Override
    public List<Curso> view(Long userId) {
        return cursoRepository.findByUserId(userId);
    }

    /**
     * Cria um novo curso associado a um usuário.
     *
     * @param curso  o objeto Curso a ser criado
     * @param userId o ID do usuário ao qual o curso será associado
     * @return o curso recém-criado e salvo no repositório
     */
    @Override
    public Curso create(Curso curso, Long userId) {
        Vitima vitima = vitimaRepository.findByUserId(userId);
        curso.setVitima(vitima);
        return cursoRepository.save(curso);
    }

    /**
     * Atualiza um curso existente de um usuário.
     *
     * @param curso   o objeto Curso com os dados atualizados
     * @param userId  o ID do usuário que possui o curso
     * @param cursoId o ID do curso a ser atualizado
     * @return o curso atualizado e salvo no repositório
     * @throws RuntimeException se o curso não estiver cadastrado
     */
    @Override
    public Curso update(Curso curso, Long userId, Long cursoId) {
        Vitima vitima = vitimaRepository.findByUserId(userId);
        Curso edit_curso = cursoRepository.findById(cursoId).orElseThrow(()
            -> new RuntimeException("Curso nao cadastrado"));

        edit_curso.setEmpresa_curso(curso.getEmpresa_curso());
        edit_curso.setConteudo(curso.getConteudo());
        edit_curso.setVitima(vitima);
        edit_curso.setNome(curso.getNome());
        edit_curso.setHoras(curso.getHoras());

        return cursoRepository.save(edit_curso);
    }
}
