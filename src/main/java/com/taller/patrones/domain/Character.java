package com.taller.patrones.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un personaje en combate.
 */
public class Character {

    /**
     * ¿Por qué quieres que los parámetros sean final? ¿No te interesa que puedan cambiar? ¿Por qué?
     */
    private String name;
    private int maxHp;
    private int attack;
    private int defense;
    private int speed;
    private int currentHp;
    private List<String> equipment;
    private List<String> buff;
    private List<String> temp;
    private HeroClass heroClass;

    public Character() { //Necesitas un constructor vacío
    }

    private Character(String name, int maxHp, int attack, int defense, int speed) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    /**
     * Falta el método estático para poder usar el builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void takeDamage(int damage) {
        this.currentHp = Math.max(0, currentHp - damage);
    }

    public void restoreHp(int amount) {
        this.currentHp = Math.min(maxHp, currentHp + amount);
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public double getHpPercentage() {
        return maxHp > 0 ? (double) currentHp / maxHp * 100 : 0;
    }

    public enum HeroClass {
        WARRIOR, WIZARD
    }

    public static class Builder {
        private String name;
        private int maxHp = 150;
        private int attack = 25;
        private int defense = 15;
        private int speed = 20;
        /**
         * El enunciado te pide añadir nuevos parámetros de clase
         */
        private List<String> equipment;
        private List<String> buff;
        private List<String> temp;
        private HeroClass heroClass;

        /**
         * E Builder no necesita un cosntructor. EL name se haría de otra forma
         * public Builder(String name) {
         * this.name = name;
         * }
         */

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder maxHp(int maxHp) {
            this.maxHp = maxHp;
            return this;
        }

        public Builder attack(int attack) {
            this.attack = attack;
            return this;
        }

        public Builder defense(int defense) {
            this.defense = defense;
            return this;
        }

        public Builder speed(int speed) {
            this.speed = speed;
            return this;
        }

        public Builder equipment(List<String> equipment) {
            this.equipment = equipment;
            return this;
        }

        public Builder buff(List<String> buff) {
            this.buff = buff;
            return this;
        }

        public Builder temp(List<String> temp) {
            this.temp = temp;
            return this;
        }

        public Builder heroClass(HeroClass heroClass) {
            this.heroClass = heroClass;
            return this;
        }

        /**
         * Puedes reutilizar el constructor, pero aprovechemos que tienes setters
         */
        public Character build() {
            Character character = new Character();
            character.name = name;
            character.maxHp = maxHp;
            character.currentHp = maxHp;
            character.attack = attack;
            character.defense = defense;
            character.speed = speed;
            character.equipment = equipment != null ? equipment : new ArrayList<>(); //Valores por defecto
            character.buff = buff != null ? buff : new ArrayList<>();
            character.temp = temp != null ? temp : new ArrayList<>();
            character.heroClass = heroClass != null ? heroClass : HeroClass.WARRIOR;
            return character;
        }
    }
}
