package ufpa.libertapp.vitima;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public Vitima create(@RequestBody Vitima vitima,  @RequestParam Long userId) {
        return vitimaService.create(vitima, userId);
    }

    @PreAuthorize("hasRole('PRODUCT_UPDATE')")
    @PutMapping("/{cpf}")
    public ResponseEntity<Vitima> update(@RequestBody Vitima vitima, @PathVariable String cpf) {
        Vitima updatedVitima = vitimaService.update(vitima, cpf);
        return ResponseEntity.ok(updatedVitima);
    }

    @PreAuthorize("hasRole('PRODUCT_DELETE')")
    @DeleteMapping
    public void delete(@RequestParam("cpf") String cpf) {
        vitimaService.delete(cpf);
    }
}
