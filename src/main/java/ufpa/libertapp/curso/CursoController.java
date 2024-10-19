package ufpa.libertapp.curso;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
public class CursoController {
    private final CursoService cursoService;

    @GetMapping("/{user_id}")
    public List<Curso> view(@PathVariable("user_id") Long userId) {
        return cursoService.view(userId);
    }

    @PostMapping("create/{user_id}")
    public ResponseEntity<Curso> create(@RequestBody Curso curso, @PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(cursoService.create(curso, userId));
    }
}
