package ufpa.libertapp.orgao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orgao")
public class OrgaoController {

    private final OrgaoService orgaoService;

    @Autowired
    public OrgaoController(OrgaoService orgaoService) {
        this.orgaoService = orgaoService;
    }

    // Visualizar um órgão por ID
    @GetMapping("/{id}")
    public ResponseEntity<Orgao> view(@PathVariable Long id) {
        Orgao orgao = orgaoService.view(id);
        return ResponseEntity.ok(orgao);
    }

    // Criar um novo órgão
    @PostMapping("/create/{userId}")
    public ResponseEntity<Orgao> create(
            @PathVariable Long userId,
            @RequestBody Orgao orgao) {
        Orgao createdOrgao = orgaoService.create(orgao, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrgao);
    }

    // Atualizar um órgão existente
    @PutMapping("/update/{id}")
    public ResponseEntity<Orgao> update(
            @PathVariable Long id,
            @RequestBody Orgao orgao) {
        Orgao updatedOrgao = orgaoService.update(orgao, id);
        return ResponseEntity.ok(updatedOrgao);
    }

    // Deletar um órgão por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orgaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}