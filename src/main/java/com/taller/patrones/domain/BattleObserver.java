package com.taller.patrones.domain;

import com.taller.patrones.domain.attack.Attack;

@FunctionalInterface
public interface BattleObserver {
    void onDamageApplied(Character attacker, Character defender, int damage, Attack attack);
}
