package com.taller.patrones.domain.attack;

/**
 * Fabrica de ataques. Encapsula la creación/obtención de objetos Attack.
 * Delega en el catálogo para obtener las instancias compartidas (Flyweight).
 */
public class AttackFactory {

    // No estás dándole la responsabilidad de crear al factory, lo hace todo el enum. El factory aquí sobraría, o lo que
    // sobraría sería el enum... pero no puedes quedarte a mitad.
    public static Attack createAttack(String name) {
        return AttackCatalog.fromName(name);
    }
}
