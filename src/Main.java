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
        Pokemon charmanderA = examples.getCharmander().setName("Charmander A");
        Pokemon squirtleA = examples.getSquirtle().setName("Squirtle A");
        Pokemon bulbasaurA = examples.getBulbasaur().setName("Bulbasaur A");
        Pokemon pikachuA = examples.getPikachu().setName("Pikachu A");

        Pokemon charmanderB = examples.getCharmander().setName("Charmander B");
        Pokemon squirtleB = examples.getSquirtle().setName("Squirtle B");
        Pokemon bulbasaurB = examples.getBulbasaur().setName("Bulbasaur B");
        Pokemon pikachuB = examples.getPikachu().setName("Pikachu B");

        ArrayList<Pokemon> partyA = new ArrayList<Pokemon>();

        partyA.add(bulbasaurA);
        partyA.add(charmanderA);
        partyA.add(squirtleA);
        partyA.add(pikachuA);
        ArrayList<Pokemon> partyB = new ArrayList<Pokemon>();
        partyB.add(squirtleB);
        partyB.add(pikachuB);
        partyB.add(charmanderB);
        partyB.add(bulbasaurB);

        Trainer trainerA = new Trainer(partyA);
        Trainer trainerB = new Trainer(partyB);

        Match match = new Match(trainerA, trainerB);

        match.execute();
    }

}
