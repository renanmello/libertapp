package ufpa.libertapp.vitima;

import ufpa.libertapp.product.Product;

public interface VitimaService {
    Vitima viewDados(String cpf);
    Vitima create( Vitima vitima, Long userId);
    Vitima update( Vitima vitima, String cpf);
    void delete(String cpf);

}
