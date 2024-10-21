package ufpa.libertapp.vitima;

import lombok.Getter;

@Getter
public enum VitimaHorarios {
    MANHA("manha"),
    TARDE("tarde"),
    NOITE("noite"),
    INTEGRAL("integral");

    private String horario;

    VitimaHorarios(String horario) {
        this.horario = horario;
    }
}
