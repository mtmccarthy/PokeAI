package Pokemon.PokeEffect;

import Pokemon.AttackerDefenderPair;
import Pokemon.PokeStats.PokeStatType;
import Pokemon.Pokemon;

import Error.InvalidPokemonError;
import Error.InvalidModifier;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class DecreaseOpponent implements PokeEffect{

    int scale;
    PokeStatType type;

    public DecreaseOpponent(int scale, PokeStatType type) throws InvalidModifier{
        if(scale <=0 || scale > 6) {
            throw new InvalidModifier();
        }
        else {
            this.scale = scale;
            this.type = type;
        }
    }

    @Override
    public AttackerDefenderPair effect(boolean displayPrompt, Pokemon attacker, Pokemon defender) throws InvalidPokemonError, InvalidModifier {
        Pokemon newDefender = defender.decreaseStat(displayPrompt, this.scale, this.type);

        return new AttackerDefenderPair(displayPrompt,attacker, newDefender);
    }
}
