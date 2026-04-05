package com.taller.patrones.domain;

/**
 * Interfaz Target para el patrón Adapter.
 * Permite obtener los luchadores sin importar el origen de los datos. -> Bien pero cambiale el nombre
 */
public interface CharacterMap {
    Character getPlayer();

    Character getEnemy();
}
