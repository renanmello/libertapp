package ufpa.libertapp.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    // Visualizar uma empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> view(@PathVariable Long id) {
        Empresa empresa = empresaService.view(id);
        return ResponseEntity.ok(empresa);
    }

    // Criar uma nova empresa
    @PostMapping("/create/{userId}/{orgaoId}")
    public ResponseEntity<Empresa> create(
            @PathVariable Long userId,
            @PathVariable Long orgaoId,
            @RequestBody Empresa empresa) {
        Empresa createdEmpresa = empresaService.create(empresa, userId, orgaoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmpresa);
    }

    // Atualizar uma empresa existente
    @PutMapping("/update/{id}")
    public ResponseEntity<Empresa> update(
            @PathVariable Long id,
            @RequestBody Empresa empresa) {
        Empresa updatedEmpresa = empresaService.update(empresa, id);
        return ResponseEntity.ok(updatedEmpresa);
    }

    // Deletar uma empresa por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}