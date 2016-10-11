package PokeMove;

import Pokemon.PokeEffect.PercentBurn;
import Pokemon.PokeEffect.PercentLowerStat;
import Pokemon.PokeStats.PokeStatType;
import Pokemon.PokeType;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PMBubble extends PokeMove{

    public PMBubble(){
        super(40, 100, 30, new PercentLowerStat(10, PokeStatType.SPEED, 1), PokeType.WATER, "Bubble");
    }
}
