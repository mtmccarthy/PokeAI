package Pokemon.PokeEffect;

import Pokemon.AttackerDefenderPair;
import Pokemon.Pokemon;

import Error.InvalidPokemonError;
import Error.InvalidModifier;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PercentParalysis implements PokeEffect{

    int percent;//Percent change of paralysis

    public PercentParalysis(int percent) {
        this.percent = percent;
    }
    @Override
    public AttackerDefenderPair effect(Pokemon attacker, Pokemon defender) throws InvalidPokemonError, InvalidModifier {

        int modulo = 100/ this.percent;

        int rannum = attacker.getRan().nextInt(modulo);

        AttackerDefenderPair pair;

        if(rannum == 0) {
            Pokemon newDefender = defender.paralyze();
            pair = new AttackerDefenderPair(attacker, newDefender);
        }
        else {
            pair = new AttackerDefenderPair(attacker, defender);
        }


        return pair;
    }
}
