package ufpa.libertapp.vitima;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vitima")
@RequiredArgsConstructor
public class VitimaController {

    private final VitimaService vitimaService;


    @GetMapping
    public Vitima viewDados(@RequestParam("user_id") Long id) {

        return vitimaService.viewDados(id);
    }

    @GetMapping("/all")
    public List<Vitima> viewAll(){
        return vitimaService.viewAll();
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<Vitima> create(@RequestBody Vitima vitima, @PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(vitimaService.create(vitima, user_id));
    }


    @PutMapping("update/{user_id}")
    public ResponseEntity<Vitima> update(@RequestBody Vitima vitima, @PathVariable("user_id") Long id) {
        Vitima updatedVitima = vitimaService.update(vitima, id);
        return ResponseEntity.ok(updatedVitima);
    }


    @DeleteMapping
    public void delete(@RequestParam("cpf") String cpf) {
        vitimaService.delete(cpf);
    }
}
