package ufpa.libertapp.experienciatrabalho;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ufpa.libertapp.vitima.VitimaRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/experiencia")
@RequiredArgsConstructor
public class ExperienciaTrabalhoController {

    private final ExperienciaTrabalhoService experienciaTrabalhoService;


    @GetMapping("/{user_id}")
    public List<ExperienciaTrabalho> view(@PathVariable("user_id") Long user_id) {
        return experienciaTrabalhoService.view(user_id);
    }


    @PostMapping("create/{user_id}")
    public ResponseEntity<ExperienciaTrabalho> create(@RequestBody ExperienciaTrabalho experienciaTrabalho, @PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(experienciaTrabalhoService.create(experienciaTrabalho, user_id));
    }

    @PutMapping("update/{user_id}/{exp_id}")
    public ResponseEntity<ExperienciaTrabalho> update(@RequestBody ExperienciaTrabalho experienciaTrabalho, @PathVariable("user_id") Long user_id, @PathVariable("exp_id") Long exp_id){
        return ResponseEntity.ok(experienciaTrabalhoService.update(experienciaTrabalho,user_id,exp_id));
    }

}
