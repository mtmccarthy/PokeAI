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

    int currentAIndex;
    int currentBIndex;

    boolean gameOn; //True if both players have at least one pokemon with some health

    public Match(Trainer a, Trainer b) {
        this.playerA = a;
        this.playerB = b;
        this.currentA = (Pokemon) this.playerA.getParty().get(0);
        this.currentB = (Pokemon) this.playerB.getParty().get(0);
        this.currentA.setIndex(0);
        this.currentB.setIndex(0);
        this.currentAIndex = 0;
        this.currentBIndex = 0;

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
            if(!gameOn) {
                if(currentA.getStats().getHitPoints().getBase() <= 0){
                    playerA.getParty().set(currentAIndex, currentA);
                    currentA = playerA.nextPokemon();

                }
                else if(currentB.getStats().getHitPoints().getBase() <= 0) {
                    playerB.getParty().set(currentBIndex, currentB);
                    currentB = playerB.nextPokemon();
                }
                if(currentA == null && currentB == null) {
                    System.out.println("Game ended in a tie.");
                }
                else if(currentA == null) {
                    System.out.println("Player B won the game!");
                    return;
                }
                else if(currentB == null) {
                    System.out.println("Player A won the game");
                    return;
                }
                else {
                    currentAIndex = currentA.getIndex();
                    currentBIndex = currentB.getIndex();
                    gameOn = true;
                }
            }

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
