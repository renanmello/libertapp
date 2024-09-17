package ufpa.libertapp.vitima;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ufpa.libertapp.product.Product;

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

    @PreAuthorize("hasRole('PRODUCT_UPDATE')")
    @PutMapping
    public Vitima update(@RequestBody Vitima vitima) {
        return vitimaService.update(vitima);
    }

    @PreAuthorize("hasRole('PRODUCT_DELETE')")
    @DeleteMapping
    public void delete(@RequestParam("cpf") String cpf) {
        vitimaService.delete(cpf);
    }
}
