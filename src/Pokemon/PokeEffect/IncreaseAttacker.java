package Pokemon.PokeEffect;

import Pokemon.AttackerDefenderPair;
import Pokemon.PokeStats.PokeStatType;
import Pokemon.Pokemon;

import Error.InvalidModifier;
import Error.InvalidPokemonError;


/**
 * Created by mtmccarthy on 10/10/16.
 */
public class IncreaseAttacker implements PokeEffect{

    PokeStatType type;
    int scale;

    public IncreaseAttacker(int scale, PokeStatType type) throws InvalidModifier{
        if(scale <=0 || scale > 6) {
            throw new InvalidModifier();
        }
        else {
            this.scale = scale;
            this.type = type;
        }
    }

    @Override
    public AttackerDefenderPair effect(Pokemon attacker, Pokemon defender) throws InvalidPokemonError, InvalidModifier {
        Pokemon newAttacker = attacker.increaseStat(this.scale, this.type);

        return new AttackerDefenderPair(newAttacker, defender);
    }
}
