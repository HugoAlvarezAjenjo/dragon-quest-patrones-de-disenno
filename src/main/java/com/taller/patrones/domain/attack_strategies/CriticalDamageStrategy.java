package com.taller.patrones.domain.attack_strategies;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.Character;
import java.util.Random;

public class CriticalDamageStrategy implements DamageStrategy {
    private final Random random = new Random();

    @Override
    public int calculate(Character attacker, Character defender, Attack attack) {
        int raw = attacker.getAttack() * attack.getBasePower() / 100;
        int damage = Math.max(1, raw - defender.getDefense());

        // 20% de probabilidad de crítico (daño * 1.5)
        if (random.nextInt(100) < 20) {
            return (int) (damage * 1.5);
        }
        return damage;
    }
}
