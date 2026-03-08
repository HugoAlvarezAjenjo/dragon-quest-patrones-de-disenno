package com.taller.patrones.domain;

/**
 * Catálogo de ataques disponibles en el juego.
 * Aplica el patrón Flyweight guardando instancias únicas de Attack para
 * reutilizarlas.
 */
public enum AttackCatalog {
    TACKLE(new Attack("Tackle", 40, Attack.AttackType.NORMAL)),
    SLASH(new Attack("Slash", 55, Attack.AttackType.NORMAL)),
    FIREBALL(new Attack("Fireball", 80, Attack.AttackType.SPECIAL)),
    ICE_BEAM(new Attack("Ice Beam", 70, Attack.AttackType.SPECIAL)),
    POISON_STING(new Attack("Poison Sting", 20, Attack.AttackType.STATUS)),
    THUNDER(new Attack("Thunder", 90, Attack.AttackType.SPECIAL)),
    METEORO(new Attack("Meteoro", 120, Attack.AttackType.SPECIAL)),
    DEFAULT(new Attack("Golpe", 30, Attack.AttackType.NORMAL));

    private final Attack attack;

    AttackCatalog(Attack attack) {
        this.attack = attack;
    }

    public Attack getAttack() {
        return attack;
    }

    /**
     * Busca un ataque por su nombre y devuelve la instancia compartida.
     * 
     * @param name Nombre del ataque.
     * @return La instancia de Attack (Flyweight).
     */
    public static Attack fromName(String name) {
        if (name == null)
            return DEFAULT.getAttack();
        try {
            return valueOf(name.toUpperCase()).getAttack();
        } catch (IllegalArgumentException e) {
            return DEFAULT.getAttack();
        }
    }
}
