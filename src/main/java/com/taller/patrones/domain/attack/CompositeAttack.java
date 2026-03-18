package com.taller.patrones.domain.attack;

import com.taller.patrones.domain.Character;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ataque compuesto que agrupa otros ataques (Patrón Composite).
 */
public class CompositeAttack extends Attack {

    private final List<Attack> attacks = new ArrayList<>();

    public CompositeAttack(String name) {
        super(name, 0, AttackType.NORMAL);
    }

    public void addAttack(Attack attack) {
        attacks.add(attack);
    }

    @Override
    public int calculateDamage(Character attacker, Character defender) {
        return attacks.stream()
                .mapToInt(a -> a.calculateDamage(attacker, defender))
                .sum();
    }

    @Override
    public String getName() {
        if (attacks.isEmpty()) return super.getName();
        return super.getName() + " [" + attacks.stream().map(Attack::getName).collect(Collectors.joining(" + ")) + "]";
    }
}
