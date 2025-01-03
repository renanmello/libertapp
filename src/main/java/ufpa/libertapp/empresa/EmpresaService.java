package ufpa.libertapp.empresa;



public interface EmpresaService {
    Empresa view(Long cnpj);

    Empresa create(Empresa empresa, Long userId, Long orgaoId);

    Empresa update(Empresa empresa, Long cnpj);

    void delete(Long cnpj);
}
