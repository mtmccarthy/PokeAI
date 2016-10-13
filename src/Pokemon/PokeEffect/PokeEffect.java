package Pokemon.PokeEffect;


import Pokemon.Pokemon;
import Pokemon.AttackerDefenderPair;

import Error.InvalidPokemonError;
import Error.InvalidModifier;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public interface PokeEffect {


    public AttackerDefenderPair effect(boolean displayPrompt, Pokemon attacker, Pokemon defender) throws InvalidPokemonError, InvalidModifier;


}
