package com.taller.patrones.domain;

/**
 * Interfaz para el patrón BattleCommand.
 * <p>
 * Bien, aunque ponle un nombre más concreto
 */
public interface BattleCommand {
    void execute();

    void undo();
}
