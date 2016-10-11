package PokeMove;

import Pokemon.PokeEffect.PercentParalysis;
import Pokemon.PokeEffect.StandardSleep;
import Pokemon.PokeType;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PMParalysisPowder extends PokeMove{
    public PMParalysisPowder(){
        super(0, 65, 20, new PercentParalysis(100), PokeType.NOTYPE, "Paralysis Powder");
    }
}
