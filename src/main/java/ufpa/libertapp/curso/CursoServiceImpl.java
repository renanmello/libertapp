package ufpa.libertapp.curso;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ufpa.libertapp.vitima.Vitima;
import ufpa.libertapp.vitima.VitimaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService{

    private final VitimaRepository vitimaRepository;
    private final CursoRepository cursoRepository;

    @Override
    public List<Curso> view(Long userId) {
        return cursoRepository.findByUserId(userId);
    }

    @Override
    public Curso create(Curso curso, Long userId) {
        Vitima vitima = vitimaRepository.findByUserId(userId);
        curso.setVitima(vitima);
        return cursoRepository.save(curso);
    }

    @Override
    public Curso update(Curso curso, Long userId, Long cursoId) {
        Vitima vitima = vitimaRepository.findByUserId(userId);
        Curso edit_curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso nao cadastrado"));

        edit_curso.setEmpresa_curso(curso.getEmpresa_curso());
        edit_curso.setConteudo(curso.getConteudo());
        edit_curso.setVitima(vitima);
        edit_curso.setNome(curso.getNome());
        edit_curso.setHoras(curso.getHoras());


        return cursoRepository.save(edit_curso);
    }





}
