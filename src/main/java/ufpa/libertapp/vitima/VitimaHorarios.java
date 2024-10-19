package ufpa.libertapp.vitima;

import lombok.Getter;

@Getter
public enum VitimaHorarios {
    MANHÃ("manhã"),
    TARDE("tarde"),
    NOITE("noite"),
    INTEGRAL("integral");

    private String horario;

    VitimaHorarios(String horario) {
        this.horario = horario;
    }
}
