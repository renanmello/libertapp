package ufpa.libertapp.empresa;



public interface EmpresaService {
    Empresa view(Long id);

    Empresa create(Empresa empresa, Long id);

    Empresa update(Empresa empresa, Long id);

    void delete(Long id);
}
