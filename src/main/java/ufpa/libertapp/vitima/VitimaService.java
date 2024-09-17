package ufpa.libertapp.vitima;

import ufpa.libertapp.product.Product;

public interface VitimaService {
    Vitima viewDados(String cpf);
    Vitima create( Vitima vitima);
    Vitima update( Vitima vitima);
    void delete(String cpf);

}
