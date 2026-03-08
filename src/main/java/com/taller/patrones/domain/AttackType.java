package com.taller.patrones.domain;

import com.taller.patrones.domain.attack_strategies.CriticalDamageStrategy;
import com.taller.patrones.domain.attack_strategies.DamageStrategy;
import com.taller.patrones.domain.attack_strategies.NormalDamageStrategy;
import com.taller.patrones.domain.attack_strategies.SpecialDamageStrategy;
import com.taller.patrones.domain.attack_strategies.StatusDamageStrategy;

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
