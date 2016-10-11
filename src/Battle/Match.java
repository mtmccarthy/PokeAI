package Battle;

import PokeMove.PokeMove;
import Pokemon.PokeStats.PokeStat;
import Pokemon.Pokemon;

import Error.InvalidModifier;
import Error.InvalidPokemonError;

import Pokemon.AttackerDefenderPair;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class Match {

    Trainer playerA;
    Trainer playerB;

    Pokemon currentA;
    Pokemon currentB;

    boolean gameOn; //True if both players have at least one pokemon with some health

    public Match(Trainer a, Trainer b) {
        this.playerA = a;
        this.playerB = b;
        this.currentA = (Pokemon) this.playerA.getParty().get(0);
        this.currentB = (Pokemon) this.playerB.getParty().get(0);

        this.gameOn = true;
    }

    public boolean valid(){
        return playerA.valid() && playerB.valid();
    }

    public void execute() throws InvalidModifier, InvalidPokemonError{

        while(gameOn) {
            AttackerDefenderPair pair = this.battle(currentA, currentB, playerA, playerB);
            gameOn = pair.isGameOn();
            currentA = pair.getAttacker();
            currentB = pair.getDefender();
        }
    }

    /**
     *
     * @return True when the battle should still continue
     */
    public AttackerDefenderPair battle(Pokemon pokemonA, Pokemon pokemonB, Trainer playerA, Trainer playerB) throws InvalidModifier, InvalidPokemonError{

        int speedA = (int) pokemonA.getStats().getSpeed().getModifiedStat();
        int speedB = (int) pokemonB.getStats().getSpeed().getModifiedStat();

        PokeMove moveA = pokemonA.chooseMove(pokemonB);
        PokeMove moveB = pokemonB.chooseMove(pokemonB);

        if(speedA > speedB){
            System.out.println(pokemonA.getName() + " went first");
            System.out.println(pokemonA.getName() + " used " + moveA.getName());
            AttackerDefenderPair pair = pokemonA.attack(pokemonB, moveA); //A attacks B
            boolean keepGoing = pair.isGameOn();

            if(keepGoing) {
                System.out.println(pokemonB.getName() + " used " + moveB.getName());
                AttackerDefenderPair newPair = pair.getDefender().attack(pair.getAttacker(), moveB);

                return new AttackerDefenderPair(newPair.getAttacker(), newPair.getDefender());
            }
            else {
                return pair;
            }

        }
        else if(speedB > speedA){
            System.out.println(pokemonB.getName() + " went first");
            System.out.println(pokemonB.getName() + " used " + moveB.getName());
            AttackerDefenderPair pair = pokemonB.attack(pokemonA, moveB); //A attacks B
            boolean keepGoing = pair.isGameOn();

            if(keepGoing) {
                System.out.println(pokemonA.getName() + " used " + moveA.getName());
                AttackerDefenderPair newPair = pair.getDefender().attack(pair.getAttacker(), moveA);
                return new AttackerDefenderPair(newPair.getAttacker(), newPair.getDefender());
            }
            else {
                return new AttackerDefenderPair(pair.getDefender(), pair.getAttacker());
            }

        }
        else {
            int x = (int) (Math.random() % 2);
            if(x == 0){
                System.out.println(pokemonA.getName() + " went first");
                System.out.println(pokemonA.getName() + " used " + moveA.getName());
                AttackerDefenderPair pair = pokemonA.attack(pokemonB, moveA); //A attacks B
                boolean keepGoing = pair.isGameOn();

                if(keepGoing) {
                    System.out.println(pokemonB.getName() + " used " + moveB.getName());
                    AttackerDefenderPair newPair = pair.getDefender().attack(pair.getAttacker(), moveB);
                    return new AttackerDefenderPair(newPair.getDefender(), newPair.getAttacker());
                }
                else {
                    return pair;
                }
            }
            else {
                System.out.println(pokemonB.getName() + " went first");
                System.out.println(pokemonB.getName() + " used " + moveB.getName());
                AttackerDefenderPair pair = pokemonB.attack(pokemonA, moveB); //A attacks B
                boolean keepGoing = pair.isGameOn();

                if(keepGoing) {
                    System.out.println(pokemonA.getName() + " used " + moveA.getName());
                    AttackerDefenderPair newPair = pair.getDefender().attack(pair.getAttacker(), moveA);
                    return new AttackerDefenderPair(newPair.getAttacker(), newPair.getDefender());
                }
                else {
                    return new AttackerDefenderPair(pair.getDefender(), pair.getAttacker());
                }
            }
        }
    }
}
