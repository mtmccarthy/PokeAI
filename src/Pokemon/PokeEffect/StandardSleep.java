package Pokemon.PokeEffect;

import Pokemon.Pokemon;
import Pokemon.AttackerDefenderPair;

import Error.InvalidPokemonError;
import Error.InvalidModifier;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class StandardSleep implements PokeEffect{


    @Override
    public AttackerDefenderPair effect(boolean displayPrompt, Pokemon attacker, Pokemon defender) throws InvalidPokemonError, InvalidModifier{
        Pokemon newDefender = defender.normalSleep(displayPrompt);

        return new AttackerDefenderPair(displayPrompt, attacker, newDefender);
    }

}
