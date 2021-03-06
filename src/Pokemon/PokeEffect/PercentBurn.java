package Pokemon.PokeEffect;

import Pokemon.AttackerDefenderPair;
import Pokemon.Pokemon;

import Error.InvalidPokemonError;

/**
 * Created by Nguttitehem on 10/10/16.
 */
public class PercentBurn implements PokeEffect{

    int percent;//Percent change of paralysis

    public PercentBurn(int percent) {
        this.percent = percent;
    }
    @Override
    public AttackerDefenderPair effect(boolean displayPrompt, Pokemon attacker, Pokemon defender) throws InvalidPokemonError, InvalidPokemonError {

        int modulo = 100/ this.percent;

        int rannum = (int) Math.random() % modulo;

        AttackerDefenderPair pair;

        if(rannum == 0) {
            Pokemon newDefender = defender.burn(displayPrompt);
            pair = new AttackerDefenderPair(displayPrompt, attacker, newDefender);
        }
        else {
            pair = new AttackerDefenderPair(displayPrompt, attacker, defender);
        }


        return pair;
    }
}
