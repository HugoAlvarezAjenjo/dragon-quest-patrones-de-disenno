package com.taller.patrones.domain;

/**
 * Fabrica de ataques. Encapsula la creación/obtención de objetos Attack.
 * Delega en el catálogo para obtener las instancias compartidas (Flyweight).
 */
public class AttackFactory {

    public static Attack createAttack(String name) {
        return AttackCatalog.fromName(name);
    }
}
