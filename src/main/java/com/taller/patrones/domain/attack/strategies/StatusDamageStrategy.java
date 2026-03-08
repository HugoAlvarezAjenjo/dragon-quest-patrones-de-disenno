package com.taller.patrones.domain.attack.strategies;

import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.attack.Attack;

public class StatusDamageStrategy implements DamageStrategy {
    @Override
    public int calculate(Character attacker, Character defender, Attack attack) {
        return 0;
    }
}
