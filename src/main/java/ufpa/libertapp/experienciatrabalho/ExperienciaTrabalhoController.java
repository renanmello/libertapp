package ufpa.libertapp.experienciatrabalho;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/exptrabalho")
@RequiredArgsConstructor
public class ExperienciaTrabalhoController {
    private final ExperienciaTrabalhoService experienciaTrabalhoService;

    @PreAuthorize("hasRole('PRODUCT_SELECT')")
    @GetMapping("/view")
    public Optional<ExperienciaTrabalho> view(@RequestParam("id") Long id) {
        return experienciaTrabalhoService.view(id);
    }

    @PreAuthorize("hasRole('PRODUCT_INSERT')")
    @PostMapping("/create")
    public ExperienciaTrabalho create(@RequestBody ExperienciaTrabalho experienciaTrabalho, @RequestParam String cpf){
        return experienciaTrabalhoService.create(experienciaTrabalho, cpf);
    }
}
