package ufpa.libertapp.vitima;

import lombok.Getter;

/**
 * Enumeração que representa os horários disponíveis para uma vítima.
 * <p>
 * Inclui períodos como manhã, tarde, noite e integral, indicando
 * a disponibilidade da vítima.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Getter
public enum VitimaHorarios {
    /**
     * Disponibilidade no período da manhã.
     */
    MANHA("manha"),
    /**
     * Disponibilidade no período da tarde.
     */
    TARDE("tarde"),
    /**
     * Disponibilidade no período da noite.
     */
    NOITE("noite"),
    /**
     * Disponibilidade em período integral.
     */
    INTEGRAL("integral");
    /**
     * Representação textual do horário.
     */
    private String horario;

    /**
     * Construtor para associar uma string a cada horário.
     *
     * @param horario o texto que representa o horário
     */
    VitimaHorarios(String horario) {
        this.horario = horario;
    }
}
