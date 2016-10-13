package Pokemon.PokeEffect;

import PokeMove.PokeMove;
import Pokemon.Pokemon;
import Pokemon.AttackerDefenderPair;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class NoEffect implements PokeEffect{

    public AttackerDefenderPair effect(boolean displayPrompt, Pokemon attacker, Pokemon defender) {

        return new AttackerDefenderPair(displayPrompt, attacker, defender);
    }
}
