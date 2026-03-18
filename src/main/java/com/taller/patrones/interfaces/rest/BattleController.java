package com.taller.patrones.interfaces.rest;

import com.taller.patrones.application.CombatFacade;
import com.taller.patrones.domain.ExternalFighterAdapter;
import com.taller.patrones.domain.FighterProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/battle")
@CrossOrigin(origins = "*")
public class BattleController {

    private final CombatFacade combatFacade = new CombatFacade();

    @PostMapping("/start")
    public ResponseEntity<Map<String, Object>> startBattle(@RequestBody(required = false) Map<String, String> body) {
        String playerName = body != null && body.containsKey("playerName") ? body.get("playerName") : null;
        String enemyName = body != null && body.containsKey("enemyName") ? body.get("enemyName") : null;

        return ResponseEntity.ok(combatFacade.startBattle(playerName, enemyName));
    }

    @PostMapping("/start/external")
    public ResponseEntity<Map<String, Object>> startBattleFromExternal(@RequestBody Map<String, Object> body) {
        FighterProvider provider = new ExternalFighterAdapter(body);
        return ResponseEntity.ok(combatFacade.startBattleFromExternal(provider));
    }

    @GetMapping("/{battleId}")
    public ResponseEntity<Map<String, Object>> getBattle(@PathVariable String battleId) {
        Map<String, Object> status = combatFacade.getBattleStatus(battleId);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{battleId}/attack")
    public ResponseEntity<Map<String, Object>> attack(@PathVariable String battleId,
            @RequestBody Map<String, String> body) {
        String attackName = body != null && body.get("attack") != null ? body.get("attack") : "TACKLE";
        Map<String, Object> status = combatFacade.attack(battleId, attackName);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{battleId}/undo")
    public ResponseEntity<Map<String, Object>> undoLastAttack(@PathVariable String battleId) {
        Map<String, Object> status = combatFacade.undo(battleId);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{battleId}/enemy-turn")
    public ResponseEntity<Map<String, Object>> enemyTurn(@PathVariable String battleId) {
        Map<String, Object> status = combatFacade.executeEnemyTurn(battleId);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }
}
