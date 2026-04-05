package com.taller.patrones.domain;

import java.util.Map;

/**
 * Clase Adapter que envuelve un Map (JSON externo) y lo adapta
 * a la interfaz CharacterMap esperada por el dominio.
 * <p>
 * Bien la idea
 */
public class ExternalFighterAdapter implements CharacterMap {

    private final Map<String, Object> externalData;

    public ExternalFighterAdapter(Map<String, Object> externalData) {
        this.externalData = externalData;
    }

    @Override
    public Character getPlayer() {
        String name = (String) externalData.getOrDefault("fighter1_name", "Héroe");
        int hp = ((Number) externalData.getOrDefault("fighter1_hp", 150)).intValue();
        int atk = ((Number) externalData.getOrDefault("fighter1_atk", 25)).intValue();

        return new Character.Builder(
                name) // EL builder no necesita recibir ningún parámetro al llamarlo, pierdes el sentido y la legibilidad
                .maxHp(hp)
                .attack(atk)
                .defense(10)
                .speed(10)
                .build();
    }

    @Override
    public Character getEnemy() {
        String name = (String) externalData.getOrDefault("fighter2_name", "Dragón");
        int hp = ((Number) externalData.getOrDefault("fighter2_hp", 120)).intValue();
        int atk = ((Number) externalData.getOrDefault("fighter2_atk", 30)).intValue();

        return new Character.Builder(name)
                .maxHp(hp)
                .attack(atk)
                .defense(10)
                .speed(10)
                .build();
    }
}
