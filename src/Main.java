/**
 * Created by mtmccarthy on 10/10/16.
 */

import Battle.Match;
import Battle.Trainer;
import Examples.Examples;
import Pokemon.Pokemon;

import Error.InvalidModifier;
import Error.InvalidPokemonError;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InvalidModifier, InvalidPokemonError{
        Examples examples = new Examples();
        Pokemon charmander = examples.getCharmander();
        Pokemon squirtle = examples.getSquirtle();
        Pokemon bulbasaur = examples.getBulbasaur();
        Pokemon pikachu = examples.getPikachu();

        ArrayList<Pokemon> partyA = new ArrayList<Pokemon>();

        partyA.add(bulbasaur);
        partyA.add(squirtle);
        ArrayList<Pokemon> partyB = new ArrayList<Pokemon>();
        partyB.add(pikachu);
        partyB.add(charmander);

        Trainer trainerA = new Trainer(partyA);
        Trainer trainerB = new Trainer(partyB);

        Match match = new Match(trainerA, trainerB);

        match.execute();
    }

}
