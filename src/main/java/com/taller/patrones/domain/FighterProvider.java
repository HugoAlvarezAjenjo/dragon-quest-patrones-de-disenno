package com.taller.patrones.domain;

/**
 * Interfaz Target para el patrón Adapter.
 * Permite obtener los luchadores sin importar el origen de los datos.
 */
public interface FighterProvider {
    Character getPlayer();

    Character getEnemy();
}
