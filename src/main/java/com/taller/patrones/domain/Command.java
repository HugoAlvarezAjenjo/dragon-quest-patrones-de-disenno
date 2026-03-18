package com.taller.patrones.domain;

/**
 * Interfaz para el patrón Command.
 */
public interface Command {
    void execute();
    void undo();
}
