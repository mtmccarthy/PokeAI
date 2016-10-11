package PokeMove;

import Pokemon.PokeEffect.PercentParalysis;
import Pokemon.PokeType;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PMThunderShock extends PokeMove{

    public PMThunderShock(){
        super(40,100,30, new PercentParalysis(10),PokeType.ELECTRIC, "Thundershock");
    }

}
