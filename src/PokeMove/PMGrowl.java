package PokeMove;

import Pokemon.PokeEffect.DecreaseOpponent;
import Pokemon.PokeStats.PokeStatType;
import Pokemon.PokeType;

import Error.InvalidModifier;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PMGrowl extends PokeMove{

    public PMGrowl() throws InvalidModifier{
        super(0, 100, 30, new DecreaseOpponent(1, PokeStatType.ATTACK), PokeType.NORMAL, "Growl");
    }
}
