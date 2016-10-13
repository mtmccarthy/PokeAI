package Pokemon.PokeStats;

import Pokemon.Pokemon;

import Error.InvalidModifier;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PokeStats {

    PokeStat attack;
    PokeStat defense;
    PokeStat specialAttack;
    PokeStat specialDefense;
    PokeStat speed;
    PokeStat hitPoints;

    public PokeStats(int attack, int defense, int specialAttack, int specialDefense, int speed, int hitPoints){
        this.attack = new PokeStat(attack, PokeStatType.ATTACK);
        this.defense = new PokeStat(defense, PokeStatType.DEFENSE);
        this.specialAttack = new PokeStat(specialAttack, PokeStatType.SPECIALATTACK);
        this.specialDefense = new PokeStat(specialDefense, PokeStatType.SPECIALDEFENSE);
        this.speed = new PokeStat(speed, PokeStatType.SPEED);
        this.hitPoints = new PokeStat(hitPoints, PokeStatType.HITPOINTS);
    }

    public PokeStats(PokeStat attack, PokeStat defense, PokeStat specialAttack, PokeStat specialDefense, PokeStat speed, PokeStat hitPoints){
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.hitPoints = hitPoints;
    }

    public PokeStats increaseStat(boolean displayPrompt, PokeStatType type, int scale) throws InvalidModifier{
        if(type.equals(PokeStatType.ATTACK)) {
            return new PokeStats(this.attack.incrementCounter(displayPrompt, scale), this.defense, this.specialAttack, this.specialDefense, this.speed, this.hitPoints);
        }
        else if(type.equals(PokeStatType.DEFENSE)) {
            return new PokeStats(this.attack, this.defense.incrementCounter(displayPrompt, scale), this.specialAttack, this.specialDefense, this.speed, this.hitPoints);
        }
        else if(type.equals(PokeStatType.SPECIALATTACK)) {
            return new PokeStats(this.attack, this.defense, this.specialAttack.incrementCounter(displayPrompt, scale), this.specialDefense, this.speed, this.hitPoints);
        }
        else if(type.equals(PokeStatType.SPECIALDEFENSE)) {
            return new PokeStats(this.attack, this.defense, this.specialAttack, this.specialDefense.incrementCounter(displayPrompt, scale), this.speed, this.hitPoints);
        }
        else if(type.equals(PokeStatType.SPEED)) {
            return new PokeStats(this.attack, this.defense, this.specialAttack, this.specialDefense, this.speed.incrementCounter(displayPrompt, scale), this.hitPoints);
        }
        else {
            //SHOULD WE ADD SOMETHING
            return this;
        }
    }

    public PokeStats decreaseStat(boolean displayPrompt, PokeStatType type, int scale) throws InvalidModifier{
        if(type.equals(PokeStatType.ATTACK)) {
            return new PokeStats(this.attack.decrementCounter(displayPrompt, scale), this.defense, this.specialAttack, this.specialDefense, this.speed, this.hitPoints);
        }
        else if(type.equals(PokeStatType.DEFENSE)) {
            return new PokeStats(this.attack, this.defense.decrementCounter(displayPrompt, scale), this.specialAttack, this.specialDefense, this.speed, this.hitPoints);
        }
        else if(type.equals(PokeStatType.SPECIALATTACK)) {
            return new PokeStats(this.attack, this.defense, this.specialAttack.decrementCounter(displayPrompt, scale), this.specialDefense, this.speed, this.hitPoints);
        }
        else if(type.equals(PokeStatType.SPECIALDEFENSE)) {
            return new PokeStats(this.attack, this.defense, this.specialAttack, this.specialDefense.decrementCounter(displayPrompt, scale), this.speed, this.hitPoints);
        }
        else if(type.equals(PokeStatType.SPEED)) {
            return new PokeStats(this.attack, this.defense, this.specialAttack, this.specialDefense, this.speed.decrementCounter(displayPrompt, scale), this.hitPoints);
        }
        else {
            //SHOULD WE ADD SOMETHING
            return this;
        }
    }

    public PokeStat getAttack(){
        return this.attack;
    }
    public PokeStat getDefense(){
        return this.defense;
    }
    public PokeStat getSpecialAttack(){
        return this.specialAttack;
    }
    public PokeStat getSpecialDefense(){
        return this.specialDefense;
    }
    public PokeStat getSpeed(){
        return this.speed;
    }
    public PokeStat getHitPoints(){
        return this.hitPoints;
    }

}
