package ufpa.libertapp.vitima;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vitima")
@RequiredArgsConstructor
public class VitimaController {

    private final VitimaService vitimaService;

    @PreAuthorize("hasRole('PRODUCT_SELECT')")
    @GetMapping
    public Vitima view(@RequestParam("cpf") String cpf) {
        return vitimaService.viewDados(cpf);
    }

    @PreAuthorize("hasRole('PRODUCT_INSERT')")
    @PostMapping
    public Vitima create(@RequestBody Vitima vitima) {
        return vitimaService.create(vitima);
    }

}
