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




    @GetMapping
    public Optional<ExperienciaTrabalho> view(@RequestParam("id") Long id) {
        return experienciaTrabalhoService.view(id);
    }


    @PostMapping
    public ExperienciaTrabalho create(@RequestBody ExperienciaTrabalho experienciaTrabalho, @RequestParam String cpf){
          return experienciaTrabalhoService.create(experienciaTrabalho, cpf);
    }
}
