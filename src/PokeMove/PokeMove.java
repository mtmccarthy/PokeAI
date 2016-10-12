package PokeMove;

import Pokemon.PokeEffect.NoEffect;
import Pokemon.PokeEffect.PokeEffect;
import Pokemon.PokeType;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PokeMove {

    int damage;
    int accuracy;
    PokeEffect effect;
    int maxPowerPoints;
    int currentPowerPoints;
    PokeType type;
    String name;

    PokeMove(int damage, int accuracy, int maxPowerPoints, PokeType type, String name) {
        this.damage = damage;
        this.accuracy = accuracy;
        this.maxPowerPoints = maxPowerPoints;
        this.currentPowerPoints = maxPowerPoints;
        this.effect = new NoEffect();
        this.type = type;
        this.name = name;
    }

    PokeMove(int damage, int accuracy, int maxPowerPoints, PokeEffect effect, PokeType type, String name) {
        this.damage = damage;
        this.accuracy = accuracy;
        this.maxPowerPoints = maxPowerPoints;
        this.currentPowerPoints = maxPowerPoints;
        this.effect = effect;
        this.type = type;
        this.name = name;
    }

    public int getDamage(){
        return this.damage;
    }
    public PokeEffect getEffect(){
        return this.effect;
    }
    public PokeType getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

	public int getHeuristic() {
		
		return 0;
	}
}
