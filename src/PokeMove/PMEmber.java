package PokeMove;

import Pokemon.PokeEffect.PercentBurn;
import Pokemon.PokeType;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PMEmber extends PokeMove{

    public PMEmber(){
        super(40, 100, 30, new PercentBurn(10), PokeType.FIRE, "Ember");
    }
}
