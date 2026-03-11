package com.taller.patrones.application;

import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.BattleObserver;
import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.FighterProvider;
import com.taller.patrones.domain.attack.Attack;
import com.taller.patrones.domain.attack.AttackFactory;
import com.taller.patrones.infrastructure.combat.CombatEngine;
import com.taller.patrones.infrastructure.persistence.BattleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Caso de uso: gestionar batallas.
 * <p>
 * Nota: Crea sus propias dependencias con new. Cada vez que necesitamos
 * un CombatEngine o BattleRepository, hacemos new aquí.
 */
public class BattleService {

    private final CombatEngine combatEngine = new CombatEngine();
    private final BattleRepository battleRepository = BattleRepository.getInstance();
    private final List<BattleObserver> observers = new ArrayList<>();

    public void addObserver(BattleObserver observer) {
        observers.add(observer);
    }

    public static final List<String> PLAYER_ATTACKS = List.of("TACKLE", "SLASH", "FIREBALL", "ICE_BEAM", "POISON_STING",
            "THUNDER", "METEORO", "CRITICAL_STRIKE");
    public static final List<String> ENEMY_ATTACKS = List.of("TACKLE", "SLASH", "FIREBALL");

    public BattleStartResult startBattle(String playerName, String enemyName) {
        Character player = new Character.Builder(playerName != null ? playerName : "Héroe")
                .maxHp(150)
                .attack(25)
                .defense(15)
                .speed(20)
                .build();

        Character enemy = new Character.Builder(enemyName != null ? enemyName : "Dragón")
                .maxHp(120)
                .attack(30)
                .defense(10)
                .speed(15)
                .build();

        Battle battle = new Battle(player, enemy);
        String battleId = UUID.randomUUID().toString();
        battleRepository.save(battleId, battle);
        this.addObserver(battle);
        return new BattleStartResult(battleId, battle);
    }

    public Battle getBattle(String battleId) {
        return battleRepository.findById(battleId);
    }

    public void executePlayerAttack(String battleId, String attackName) {
        Battle battle = battleRepository.findById(battleId);
        if (battle == null || battle.isFinished() || !battle.isPlayerTurn())
            return;

        Attack attack = AttackFactory.createAttack(attackName);
        int damage = combatEngine.calculateDamage(battle.getPlayer(), battle.getEnemy(), attack);
        applyDamage(battle, battle.getPlayer(), battle.getEnemy(), damage, attack);
    }

    public void executeEnemyAttack(String battleId, String attackName) {
        Battle battle = battleRepository.findById(battleId);
        if (battle == null || battle.isFinished() || battle.isPlayerTurn())
            return;

        Attack attack = AttackFactory.createAttack(attackName != null ? attackName : "TACKLE");
        int damage = combatEngine.calculateDamage(battle.getEnemy(), battle.getPlayer(), attack);
        applyDamage(battle, battle.getEnemy(), battle.getPlayer(), damage, attack);
    }

    private void applyDamage(Battle battle, Character attacker, Character defender, int damage, Attack attack) {
        defender.takeDamage(damage);
        String target = defender == battle.getPlayer() ? "player" : "enemy";
        battle.setLastDamage(damage, target);
        observers.forEach(observer -> observer.onDamageApplied(attacker, defender, damage, attack));
        battle.switchTurn();
        if (!defender.isAlive()) {
            battle.finish(attacker.getName());
        }
    }

    /**
     * Inicia una batalla desde un origen externo usando el patrón Adapter.
     */
    public BattleStartResult startBattleFromExternal(FighterProvider provider) {
        Character player = provider.getPlayer();
        Character enemy = provider.getEnemy();

        Battle battle = new Battle(player, enemy);
        String battleId = UUID.randomUUID().toString();
        battleRepository.save(battleId, battle);
        return new BattleStartResult(battleId, battle);
    }

    public record BattleStartResult(String battleId, Battle battle) {
    }
}
