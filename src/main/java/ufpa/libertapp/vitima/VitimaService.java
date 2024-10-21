package ufpa.libertapp.vitima;


import java.util.List;

public interface VitimaService {
    Vitima viewDados(Long id);

    Vitima create(Vitima vitima, Long user_id);

    Vitima update(Vitima vitima, Long id);

    void delete(String cpf);

    List<Vitima> viewAll();

    List<VitimaDTO> findAllVitimaDetails();

    VitimaCursoExpDTO findCursoExpByUserId(Long userID);
}
