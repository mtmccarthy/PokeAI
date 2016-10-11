package PokeMove;

import Pokemon.PokeEffect.StandardSleep;
import Pokemon.PokeType;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PMSleepPowder extends PokeMove{
    public PMSleepPowder(){
        super(0, 65, 20, new StandardSleep(), PokeType.NOTYPE, "Sleep Powder");
    }
}
