package com.taller.patrones.domain.attack.strategies;

import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.attack.Attack;

public class NormalDamageStrategy implements DamageStrategy {
    @Override
    public int calculate(Character attacker, Character defender, Attack attack) {
        int raw = attacker.getAttack() * attack.getBasePower() / 100;
        return Math.max(1, raw - defender.getDefense());
    }
}
