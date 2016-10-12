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
	
	public boolean isPlayerAGoing;
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
        this.currentA.m = this;
        this.currentB = (Pokemon) this.playerB.getParty().get(0);
        this.currentB.m = this;
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
            boolean changeA = false; //A new pokemon was sent out for playerA
            boolean changeB = false; //A new pokemon was sent out for playerB
            AttackerDefenderPair pair = this.battle(currentA, currentB, playerA, playerB);
            gameOn = pair.isGameOn();
            currentA = pair.getAttacker();
            currentB = pair.getDefender();
            if(!gameOn) {

                if(currentA.getStats().getHitPoints().getBase() <= 0){

                    playerA = playerA.setParty(playerA.currentPokemon, currentA);
                    currentA = playerA.nextPokemon();
                    changeA = true;

                }
                else if(currentB.getStats().getHitPoints().getBase() <= 0) {

                    playerB = playerB.setParty(playerB.currentPokemon, currentB);
                    currentB = playerB.nextPokemon();
                    changeB = true;
                }
                if(currentA == null && currentB == null) {
                    System.out.println("Game ended in a tie.");
                    return;
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
                    this.currentA.m = this;
                    if(changeA){
                        System.out.println("Player A sent out "+ currentA.getName() + " with index " + currentAIndex);
                    }
                    currentBIndex = currentB.getIndex();
                    this.currentB.m = this;
                    if(changeB) {
                        System.out.println("Player B sent out "+ currentB.getName() + " with index " + currentBIndex);
                    }
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
            System.out.println();
            System.out.println();
            System.out.println(pokemonA.getName() + " went first");
            System.out.println();
            System.out.println(pokemonA.getName() + " used " + moveA.getName());
            AttackerDefenderPair pair = pokemonA.attack(pokemonB, moveA); //A attacks B
            boolean keepGoing = pair.isGameOn();

            if(keepGoing) {
                System.out.println(pokemonB.getName() + " used " + moveB.getName());
                AttackerDefenderPair newPair = pair.getDefender().attack(pair.getAttacker(), moveB);

                return new AttackerDefenderPair(newPair.getDefender(), newPair.getAttacker(), newPair.isGameOn());
            }
            else {

                return pair;
            }

        }
        else if(speedB > speedA){
            System.out.println();
            System.out.println();
            System.out.println(pokemonB.getName() + " went first");
            System.out.println();
            System.out.println(pokemonB.getName() + " used " + moveB.getName());
            AttackerDefenderPair pair = pokemonB.attack(pokemonA, moveB); //A attacks B
            boolean keepGoing = pair.isGameOn();

            if(keepGoing) {
                System.out.println(pokemonA.getName() + " used " + moveA.getName());
                AttackerDefenderPair newPair = pair.getDefender().attack(pair.getAttacker(), moveA);
                return new AttackerDefenderPair(newPair.getAttacker(), newPair.getDefender(), newPair.isGameOn());
            }
            else {
                return new AttackerDefenderPair(pair.getDefender(), pair.getAttacker(), pair.isGameOn());
            }

        }
        else {
            int x = pokemonA.getRan().nextInt(2);
            if(x == 0){
                System.out.println();
                System.out.println();
                System.out.println(pokemonA.getName() + " went first");
                System.out.println();
                System.out.println(pokemonA.getName() + " used " + moveA.getName());
                AttackerDefenderPair pair = pokemonA.attack(pokemonB, moveA); //A attacks B
                boolean keepGoing = pair.isGameOn();

                if(keepGoing) {
                    System.out.println(pokemonB.getName() + " used " + moveB.getName());
                    AttackerDefenderPair newPair = pair.getDefender().attack(pair.getAttacker(), moveB);
                    return new AttackerDefenderPair(newPair.getDefender(), newPair.getAttacker(), newPair.isGameOn());
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
                    return new AttackerDefenderPair(newPair.getAttacker(), newPair.getDefender(), newPair.isGameOn());
                }
                else {
                    return new AttackerDefenderPair(pair.getDefender(), pair.getAttacker(), pair.isGameOn());
                }
            }
        }
    }

	public Integer getHeuristic() {
		if (isPlayerAGoing==true)
			playerA.getHeuristic();
		else
			playerB.getHeuristic();
	}
}
