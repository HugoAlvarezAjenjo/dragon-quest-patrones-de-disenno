package com.taller.patrones.domain.attack_strategies;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.Character;

public class StatusDamageStrategy implements DamageStrategy {
    @Override
    public int calculate(Character attacker, Character defender, Attack attack) {
        return 0;
    }
}
