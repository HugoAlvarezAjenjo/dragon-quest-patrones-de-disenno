package com.taller.patrones.domain;

import com.taller.patrones.domain.attack.Attack;

/**
 * Comando concreto para ejecutar y deshacer un ataque.
 */
public class AttackCommand implements Command {
    private final Battle battle;
    private final Character attacker;
    private final Character defender;
    private final int damage;
    private final Attack attack;
    private final boolean fatal;

    public AttackCommand(Battle battle, Character attacker, Character defender, int damage, Attack attack) {
        this.battle = battle;
        this.attacker = attacker;
        this.defender = defender;
        this.damage = damage;
        this.attack = attack;
        this.fatal = (defender.getCurrentHp() - damage <= 0);
    }

    @Override
    public void execute() {
        defender.takeDamage(damage);
        battle.setLastDamage(damage, defender == battle.getPlayer() ? "player" : "enemy");
        battle.switchTurn();
        if (fatal) {
            battle.finish(attacker.getName());
        }
    }

    @Override
    public void undo() {
        defender.restoreHp(damage);
        battle.switchTurn();
        battle.removeLastLog(); // Quitar log de la acción
        if (fatal) {
            battle.undoFinish();
            battle.removeLastLog(); // Quitar log de "gana la batalla"
        }
    }
}
