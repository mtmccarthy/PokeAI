package PokeMove;

import Pokemon.PokeEffect.IncreaseAttacker;
import Pokemon.PokeStats.PokeStatType;
import Pokemon.PokeType;

import Error.InvalidModifier;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PMDefenseCurl extends PokeMove{

    public PMDefenseCurl() throws InvalidModifier{
        super(0, 0, 30, new IncreaseAttacker(1, PokeStatType.DEFENSE), PokeType.NORMAL, "Defense Curl");
    }
}
