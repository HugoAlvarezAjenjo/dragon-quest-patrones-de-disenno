package com.taller.patrones.domain.attack;

import com.taller.patrones.domain.attack.strategies.CriticalDamageStrategy;
import com.taller.patrones.domain.attack.strategies.DamageStrategy;
import com.taller.patrones.domain.attack.strategies.NormalDamageStrategy;
import com.taller.patrones.domain.attack.strategies.SpecialDamageStrategy;
import com.taller.patrones.domain.attack.strategies.StatusDamageStrategy;

/**
 * Tipos de ataque disponibles.
 * Cada tipo tiene asociada su propia estrategia de cálculo de daño.
 */
public enum AttackType {
    NORMAL(new NormalDamageStrategy()),
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
