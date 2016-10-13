package Pokemon.PokeEffect;

import Pokemon.AttackerDefenderPair;
import Pokemon.PokeStats.PokeStat;
import Pokemon.PokeStats.PokeStatType;
import Pokemon.Pokemon;

import Error.InvalidPokemonError;
import Error.InvalidModifier;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PercentLowerStat implements PokeEffect{

    int percent;//Percent change of stat change
    PokeStatType statType;
    int scale;

    public PercentLowerStat(int percent, PokeStatType statType, int scale) {

        this.percent = percent;
        this.statType = statType;
        this.scale = scale;
    }
    @Override
    public AttackerDefenderPair effect(boolean displayPrompt, Pokemon attacker, Pokemon defender) throws InvalidPokemonError, InvalidModifier{

        int modulo = 100/ this.percent;

        int rannum = (int) Math.random() % modulo;

        AttackerDefenderPair pair;

        if(rannum == 0) {
            Pokemon newDefender = defender.decreaseStat(displayPrompt, this.scale, this.statType);
            pair = new AttackerDefenderPair(displayPrompt, attacker, newDefender);
        }
        else {
            pair = new AttackerDefenderPair(displayPrompt, attacker, defender);
        }


        return pair;
    }
}
