package ufpa.libertapp.vitima;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vitima")
@RequiredArgsConstructor
public class VitimaController {

    private final VitimaService vitimaService;


    @GetMapping
    public Vitima viewDados(@RequestParam("cpf") String cpf) {

        return vitimaService.viewDados(cpf);
    }


    @PostMapping
    public Vitima create(@RequestBody Vitima vitima, @RequestParam Long userId) {
        return vitimaService.create(vitima, userId);
    }


    @PutMapping("/{user_id}")
    public ResponseEntity<Vitima> update(@RequestBody Vitima vitima, @PathVariable Long id) {
        Vitima updatedVitima = vitimaService.update(vitima, id);
        return ResponseEntity.ok(updatedVitima);
    }


    @DeleteMapping
    public void delete(@RequestParam("cpf") String cpf) {
        vitimaService.delete(cpf);
    }
}
