package ufpa.libertapp.orgao;

public interface OrgaoService {
    Orgao view(Long id);

    Orgao create(Orgao orgao, Long id);

    Orgao update(Orgao orgao, Long id);

    void delete(Long id);

}
