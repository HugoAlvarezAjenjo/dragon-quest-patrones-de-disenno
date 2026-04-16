package com.taller.patrones.domain.attack;

import com.taller.patrones.domain.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ataque compuesto que agrupa otros ataques (Patrón Composite).
 * Un Composite hace que de igual si tienes un objeto o un conjunto de objetos, se ejecutan igual.
 * Para ello necesitas que tengan una interfaz común:
 * https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/solucion-ana/src/main/java/com/taller/patrones/domain/attackFactory/AttackFactory.java
 * <p>
 * Revisa mi solución:
 * https://github.com/AnaGciaSchz/dragon-quest-patrones/commit/1ebe0503d4a9d8385003d6117f9973b1c00aed9a
 *
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
