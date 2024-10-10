package ufpa.libertapp.vitima;


public interface VitimaService {
    Vitima viewDados(Long id);

    Vitima create(Vitima vitima, Long user_id);

    Vitima update(Vitima vitima, Long id);

    void delete(String cpf);

}
