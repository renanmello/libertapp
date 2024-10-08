package ufpa.libertapp.vitima;


public interface VitimaService {
    Vitima viewDados(String cpf);

    Vitima create(Vitima vitima, Long userId);

    Vitima update(Vitima vitima, Long id);

    void delete(String cpf);

}
