package ufpa.libertapp.experienciatrabalho;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ufpa.libertapp.vitima.VitimaRepository;

import java.util.Optional;


@RestController
@RequestMapping("/experiencia")
@RequiredArgsConstructor
public class ExperienciaTrabalhoController {

    private final ExperienciaTrabalhoService experienciaTrabalhoService;


    @GetMapping("/{id}")
    public ExperienciaTrabalho view(@RequestParam("id") Long id) {
        return experienciaTrabalhoService.view(id);
    }


    @PostMapping
    public ExperienciaTrabalho create(@RequestBody ExperienciaTrabalho experienciaTrabalho, @RequestParam Long id) {
        return experienciaTrabalhoService.create(experienciaTrabalho, id);
    }
}
