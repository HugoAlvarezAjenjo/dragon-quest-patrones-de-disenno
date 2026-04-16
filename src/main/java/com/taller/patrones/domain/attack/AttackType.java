package com.taller.patrones.domain.attack;

import com.taller.patrones.domain.attack.strategies.*;

/**
 * Tipos de ataque disponibles.
 * Cada tipo tiene asociada su propia estrategia de cálculo de daño.
 */
public enum AttackType {
    NORMAL(new NormalDamageStrategy()), // Estás mezclando strategy con un enumerado... como el factory. Elige uno, meter el enumerado
    // por el medio sólo ensucia tu solución, la hace complicada sin motivo y difícil de entender.
    //Mira: https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/f9a87c7856a0218e5c6e76532527fb3cb3222a29/src/main/java/com/taller/patrones/infrastructure/combat/CombatEngine.java#L17
    // Tengo más líneas que tú, pero menos clases y es más sencillo de entender
    SPECIAL(new SpecialDamageStrategy()),
    STATUS(new StatusDamageStrategy()),
    CRITICAL(new CriticalDamageStrategy());

    private final DamageStrategy strategy;

    AttackType(DamageStrategy strategy) {
        this.strategy = strategy;
    }

    public DamageStrategy getStrategy() {
        return strategy;
    }

}
