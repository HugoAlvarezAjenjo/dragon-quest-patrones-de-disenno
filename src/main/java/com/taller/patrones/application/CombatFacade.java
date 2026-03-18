package com.taller.patrones.application;

import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.FighterProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Fachada para simplificar el acceso al sistema de combate.
 * Centraliza la orquestación de servicios y la conversión a DTOs.
 */
public class CombatFacade {

    private final BattleService battleService = new BattleService();

    public Map<String, Object> startBattle(String playerName, String enemyName) {
        var result = battleService.startBattle(playerName, enemyName);
        return toBattleStatusMap(result.battleId(), result.battle(), true);
    }

    public Map<String, Object> startBattleFromExternal(FighterProvider provider) {
        var result = battleService.startBattleFromExternal(provider);
        return toBattleStatusMap(result.battleId(), result.battle(), true);
    }

    public Map<String, Object> getBattleStatus(String battleId) {
        Battle battle = battleService.getBattle(battleId);
        if (battle == null) return null;
        return toBattleDto(battle);
    }

    public Map<String, Object> attack(String battleId, String attackName) {
        Battle battle = battleService.getBattle(battleId);
        if (battle == null) return null;

        if (battle.isPlayerTurn()) {
            battleService.executePlayerAttack(battleId, attackName);
        } else {
            battleService.executeEnemyAttack(battleId, attackName);
        }
        return toBattleDto(battle);
    }

    public Map<String, Object> undo(String battleId) {
        Battle battle = battleService.getBattle(battleId);
        if (battle == null) return null;
        
        battleService.undoLastAttack(battleId);
        return toBattleDto(battle);
    }

    public Map<String, Object> executeEnemyTurn(String battleId) {
        Battle battle = battleService.getBattle(battleId);
        if (battle == null) return null;

        if (!battle.isPlayerTurn() && !battle.isFinished()) {
            String attack = BattleService.ENEMY_ATTACKS.get((int) (Math.random() * BattleService.ENEMY_ATTACKS.size()));
            battleService.executeEnemyAttack(battleId, attack);
        }
        return toBattleDto(battle);
    }

    private Map<String, Object> toBattleStatusMap(String battleId, Battle battle, boolean isNew) {
        Map<String, Object> status = new HashMap<>(toBattleDto(battle));
        status.put("battleId", battleId);
        if (isNew) {
            status.put("lastDamage", 0);
            status.put("lastDamageTarget", "");
        }
        return status;
    }

    private Map<String, Object> toBattleDto(Battle battle) {
        return Map.of(
                "player", toCharacterDto(battle.getPlayer()),
                "enemy", toCharacterDto(battle.getEnemy()),
                "currentTurn", battle.getCurrentTurn(),
                "battleLog", battle.getBattleLog(),
                "finished", battle.isFinished(),
                "playerAttacks", BattleService.PLAYER_ATTACKS,
                "enemyAttacks", BattleService.ENEMY_ATTACKS,
                "lastDamage", battle.getLastDamage(),
                "lastDamageTarget", battle.getLastDamageTarget() != null ? battle.getLastDamageTarget() : ""
        );
    }

    private Map<String, Object> toCharacterDto(Character c) {
        return Map.of(
                "name", c.getName(),
                "currentHp", c.getCurrentHp(),
                "maxHp", c.getMaxHp(),
                "hpPercentage", c.getHpPercentage(),
                "attack", c.getAttack(),
                "defense", c.getDefense(),
                "speed", c.getSpeed(),
                "alive", c.isAlive()
        );
    }
}
