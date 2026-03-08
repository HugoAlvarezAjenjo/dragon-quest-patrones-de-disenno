package com.taller.patrones.infrastructure.combat;

import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.attack.Attack;

/**
 * Motor de combate. Calcula daño y crea ataques.
 */
public class CombatEngine {

    /**
     * Calcula el daño según la estrategia configurada directamente en el tipo de
     * ataque.
     * Patrón Strategy + Enum: delegamos la responsabilidad al tipo de ataque.
     */
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        return attack.getType().getStrategy().calculate(attacker, defender, attack);
    }
}
