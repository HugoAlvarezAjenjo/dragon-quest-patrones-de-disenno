package com.taller.patrones.domain.attack_strategies;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.Character;

/**
 * Estrategia para el cálculo de daño.
 * Patrón Strategy: encapsula el algoritmo de cálculo.
 */
@FunctionalInterface
public interface DamageStrategy {
    /**
     * Calcula el daño infligido.
     * 
     * @param attacker Personaje que ataca.
     * @param defender Personaje que defiende.
     * @param attack   Ataque realizado.
     * @return Cantidad de daño.
     */
    int calculate(Character attacker, Character defender, Attack attack);
}
