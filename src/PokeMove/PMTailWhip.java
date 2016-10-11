package PokeMove;

import Pokemon.PokeEffect.DecreaseOpponent;
import Pokemon.PokeStats.PokeStatType;

import Error.InvalidModifier;
import Pokemon.PokeType;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PMTailWhip extends PokeMove{

    public PMTailWhip() throws InvalidModifier{
        super(0,0, 30, new DecreaseOpponent(1, PokeStatType.DEFENSE), PokeType.NORMAL, "Tail Whip");
    }
}
