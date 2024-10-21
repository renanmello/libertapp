package ufpa.libertapp.curso;

import java.util.List;

public interface CursoService {
    List<Curso> view(Long userId);
    Curso create(Curso curso, Long userId);
    Curso update(Curso curso, Long userId, Long cursoId);
}
