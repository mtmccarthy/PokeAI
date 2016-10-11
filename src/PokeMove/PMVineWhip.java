package PokeMove;

import Pokemon.PokeEffect.NoEffect;
import Pokemon.PokeType;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PMVineWhip extends PokeMove{

    public PMVineWhip(){
        super(40, 100, 30, new NoEffect(), PokeType.GRASS, "Vine Whip");
    }

}
