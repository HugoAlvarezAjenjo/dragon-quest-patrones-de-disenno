package com.taller.patrones.domain;

import com.taller.patrones.domain.attack.Attack;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una batalla entre dos personajes.
 */
public class Battle implements
        BattleObserver//La idea del observer es hacer clases aparte que se suscriban y ya se notifieu, no que una clase que
//tienes, que ya tiene responsabilidades, le metas otra responsabilidad
        //Revisa esto: https://github.com/AnaGciaSchz/dragon-quest-patrones/blob/solucion-ana/src/main/java/com/taller/patrones/domain/BattleEventListener.java
{

    private final Character player;
    private final Character enemy;
    private final List<String> battleLog;
    private String currentTurn;
    private boolean finished;
    private int lastDamage;
    private String lastDamageTarget;

    public Battle(Character player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
        this.battleLog = new ArrayList<>();
        this.finished = false;
        this.currentTurn = player.getSpeed() >= enemy.getSpeed() ? "player" : "enemy";
        log("¡Comienza la batalla! " + player.getName() + " vs " + enemy.getName());
    }

    public Character getPlayer() {
        return player;
    }

    public Character getEnemy() {
        return enemy;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public List<String> getBattleLog() {
        return battleLog;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getLastDamage() {
        return lastDamage;
    }

    public String getLastDamageTarget() {
        return lastDamageTarget;
    }

    public void log(String message) {
        battleLog.add(message);
    }

    public void switchTurn() {
        currentTurn = "player".equals(currentTurn) ? "enemy" : "player";
    }

    public void finish(String winner) {
        finished = true;
        log("¡" + winner + " gana la batalla!");
    }

    public boolean isPlayerTurn() {
        return "player".equals(currentTurn);
    }

    public void setLastDamage(int damage, String target) {
        this.lastDamage = damage;
        this.lastDamageTarget = target;
    }

    public void removeLastLog() {
        if (!battleLog.isEmpty()) {
            battleLog.remove(battleLog.size() - 1);
        }
    }

    public void undoFinish() {
        this.finished = false;
    }

    @Override
    public void onDamageApplied(Character attacker, Character defender, int damage, Attack attack) {
        this.log(attacker.getName() + " usa " + attack.getName() + " y hace " + damage + " de daño a "
                + defender.getName());
    }
}
