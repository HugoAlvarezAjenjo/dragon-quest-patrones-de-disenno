package com.taller.patrones.domain.attack;

/**
 * Catálogo de ataques disponibles en el juego.
 * Aplica el patrón Flyweight guardando instancias únicas de Attack para
 * reutilizarlas.
 */
public enum AttackCatalog {
    TACKLE(new Attack("Tackle", 40, AttackType.NORMAL)),
    SLASH(new Attack("Slash", 55, AttackType.NORMAL)),
    FIREBALL(new Attack("Fireball", 80, AttackType.SPECIAL)),
    ICE_BEAM(new Attack("Ice Beam", 70, AttackType.SPECIAL)),
    POISON_STING(new Attack("Poison Sting", 20, AttackType.STATUS)),
    THUNDER(new Attack("Thunder", 90, AttackType.SPECIAL)),
    METEORO(new Attack("Meteoro", 120, AttackType.SPECIAL)),
    CRITICAL_STRIKE(new Attack("Critical Strike", 60, AttackType.CRITICAL)),
    COMBO_TRIPLE(new CompositeAttack("Triple Combo")),
    DEFAULT(new Attack("Golpe", 30, AttackType.NORMAL));

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

    static {
        // Inicializar ataques compuestos después de que todos los básicos existan
        CompositeAttack combo = (CompositeAttack) COMBO_TRIPLE.attack;
        combo.addAttack(TACKLE.attack);
        combo.addAttack(SLASH.attack);
        combo.addAttack(FIREBALL.attack);
    }
}
