package Battle;

import java.util.ArrayList;

import PokeMove.PokeMove;
import Pokemon.PokeStats.PokeStat;
import Pokemon.PokeStatus.PokeStatusType;
import Pokemon.Pokemon;
import Error.InvalidModifier;
import Error.InvalidPokemonError;
import Pokemon.AttackerDefenderPair;

/**
 * Created by mtmccarthy on 10/10/16.
 * Edited by Jacob Link
 */
public class Match {
	
	public boolean isPlayerAGoing;
    public Trainer playerA;
    public Trainer playerB;

    private Pokemon currentA;
    Pokemon currentB;

    int currentAIndex;
    int currentBIndex;

    boolean gameOn; //True if both players have at least one pokemon with some health
	private PokeMove lastMove;
	private Integer alpha;
	public Pokemon activePokeMon;

    public Match(Trainer a, Trainer b) {
        this.playerA = a;
        this.playerB = b;
        this.setCurrentA((Pokemon) this.playerA.getParty().get(0));
        this.getCurrentA().m = this;
        this.currentB = (Pokemon) this.playerB.getParty().get(0);
        this.currentB.m = this;
        this.getCurrentA().setIndex(0);
        this.currentB.setIndex(0);
        this.currentAIndex = 0;
        this.currentBIndex = 0;
        
        //start off as true for ai calculations
        this.isPlayerAGoing = true;
        
        this.gameOn = true;
    }


	public boolean valid(){
        return playerA.valid() && playerB.valid();
    }

    public void execute() throws InvalidModifier, InvalidPokemonError{

        while(gameOn) {
            boolean changeA = false; //A new pokemon was sent out for playerA
            boolean changeB = false; //A new pokemon was sent out for playerB
            AttackerDefenderPair pair = this.battle(getCurrentA(), currentB, playerA, playerB);
            gameOn = pair.isGameOn();
            setCurrentA(pair.getAttacker());
            currentB = pair.getDefender();
            if(!gameOn) {

                if(getCurrentA().getStats().getHitPoints().getBase() <= 0){

                    playerA = playerA.setParty(playerA.currentPokemon, getCurrentA());
                    setCurrentA(playerA.nextPokemon());
                    changeA = true;

                }
                else if(currentB.getStats().getHitPoints().getBase() <= 0) {

                    playerB = playerB.setParty(playerB.currentPokemon, currentB);
                    currentB = playerB.nextPokemon();
                    changeB = true;
                }
                if(getCurrentA() == null && currentB == null) {
                    System.out.println("Game ended in a tie.");
                    return;
                }
                else if(getCurrentA() == null) {
                    System.out.println("Player B won the game!");
                    return;
                }
                else if(currentB == null) {
                    System.out.println("Player A won the game");
                    return;
                }
                else {
                    currentAIndex = getCurrentA().getIndex();
                    this.getCurrentA().m = this;
                    if(changeA){
                        System.out.println("Player A sent out "+ getCurrentA().getName() + " with index " + currentAIndex);
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

        //if(moveA == null) {
        this.isPlayerAGoing = true;
            PokeMove moveA = pokemonA.chooseMove(this,pokemonB);
            
        //}

        //if(moveB == null){
            this.isPlayerAGoing = false;
            PokeMove moveB = pokemonB.chooseMove(this,pokemonA);
        //}



        if(speedA > speedB){
            System.out.println();
            System.out.println();
            System.out.println(pokemonA.getName() + " went first");
            System.out.println();
            System.out.println(pokemonA.getName() + " used " + moveA.getName());
            AttackerDefenderPair pair = pokemonA.attack(pokemonB, moveA, true); //A attacks B
            boolean keepGoing = pair.isGameOn();

            if(keepGoing) {
                System.out.println(pokemonB.getName() + " used " + moveB.getName());
                AttackerDefenderPair newPair = pair.getDefender().attack(pair.getAttacker(), moveB, true);

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
            AttackerDefenderPair pair = pokemonB.attack(pokemonA, moveB, true); //A attacks B
            boolean keepGoing = pair.isGameOn();

            if(keepGoing) {
                System.out.println(pokemonA.getName() + " used " + moveA.getName());
                AttackerDefenderPair newPair = pair.getDefender().attack(pair.getAttacker(), moveA, true);
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
                AttackerDefenderPair pair = pokemonA.attack(pokemonB, moveA, true); //A attacks B
                boolean keepGoing = pair.isGameOn();

                if(keepGoing) {
                    System.out.println(pokemonB.getName() + " used " + moveB.getName());
                    AttackerDefenderPair newPair = pair.getDefender().attack(pair.getAttacker(), moveB, true);
                    return new AttackerDefenderPair(newPair.getDefender(), newPair.getAttacker(), newPair.isGameOn());
                }
                else {
                    return pair;
                }
            }
            else {
                System.out.println(pokemonB.getName() + " went first");
                System.out.println(pokemonB.getName() + " used " + moveB.getName());
                AttackerDefenderPair pair = pokemonB.attack(pokemonA, moveB, true); //A attacks B
                boolean keepGoing = pair.isGameOn();

                if(keepGoing) {
                    System.out.println(pokemonA.getName() + " used " + moveA.getName());
                    AttackerDefenderPair newPair = pair.getDefender().attack(pair.getAttacker(), moveA, true);
                    return new AttackerDefenderPair(newPair.getAttacker(), newPair.getDefender(), newPair.isGameOn());
                }
                else {
                    return new AttackerDefenderPair(pair.getDefender(), pair.getAttacker(), pair.isGameOn());
                }
            }
        }
    }



    public Match evaluateMove(Match match, boolean playerA, PokeMove move) throws InvalidModifier, InvalidPokemonError{
            Trainer trainerA = match.playerA;
            Trainer trainerB = match.playerB;

            AttackerDefenderPair newPair;
            Integer oldH = match.getHeuristic(playerA);
            if(playerA){
                newPair = match.getCurrentA().attack(match.getCurrentB(), move, false);
            }
            else {
                newPair = match.getCurrentB().attack(match.getCurrentA(), move, false);
            }

            //Add ability to battle with chosen moves

            trainerA.setParty(0, newPair.getAttacker());
            trainerB.setParty(0, newPair.getDefender());

            
            Match newMatch = new Match(trainerA, trainerB);
            newMatch.lastMove = move;
            newMatch.alpha = newMatch.getHeuristic(newMatch.isPlayerAGoing); 
            System.out.println("Using Move "+move.getName()+", old h:"+newMatch.alpha+" vs "+oldH);
            return newMatch;


    }
    
    public Match evaluateMove(Match match, boolean playerA, Integer move) throws InvalidModifier, InvalidPokemonError{
        Trainer trainerA = match.playerA;
        Trainer trainerB = match.playerB;

        AttackerDefenderPair newPair;
        Integer oldH = match.getHeuristic(playerA);
        PokeMove lm = null;
        if(playerA){
        	PokeMove m = match.getCurrentA().getMoves().get(move);
        	lm = m;
            newPair = match.getCurrentA().attack(match.getCurrentB(), m, false);
//            System.out.println(match.getCurrentA().getName()+" is considering using "+m.getName());
            
            
        }
        else {
        	PokeMove m = match.getCurrentB().getMoves().get(move);
        	lm = m;
            newPair = match.getCurrentB().attack(match.getCurrentA(), m, false);
            System.out.println(match.getCurrentB().getName()+" is considering using "+m.getName());
        }

        //Add ability to battle with chosen moves

        trainerA.setParty(0, newPair.getAttacker());
        trainerB.setParty(0, newPair.getDefender());
        
        
        Match newMatch = new Match(trainerA, trainerB);
        newMatch.lastMove = lm;
        newMatch.alpha = newMatch.getHeuristic(newMatch.isPlayerAGoing); 
        System.out.println("Using Move "+lm.getName()+", old h:"+newMatch.alpha+" vs "+match.getHeuristic(match.isPlayerAGoing));
        return newMatch;


}
    
    

	public Integer getHeuristic(boolean playerA) {
		int heuristic = 0;
		int aSum = 0;
		int bSum = 0; 
		/*
		if (this.isPlayerAGoing==true){
			//if a is going, heuristic is health of a's pokemon - health of b's pokemon
			for(int i = 0; i < this.playerA.currentPokemon; i++){
				//set pokelist to our pokemon
				pokelist = this.playerA.getParty();
				//add to heuristic for our pokemon's hp
                Pokemon thisPoke = pokelist.get(i);
				heuristic = heuristic + thisPoke.getStats().getHitPoints().getBase();
				atotal = atotal + thisPoke.getStats().getHitPoints().getBase();
                if(!thisPoke.getStatus().getType().equals(PokeStatusType.NOSTATUS)) {
                    heuristic = heuristic - 5;
                } 
			}
			for(int j = 0; j< this.playerB.currentPokemon; j++) {
                Pokemon opposingPoke = (Pokemon) this.playerB.getParty().get(j);
                heuristic = heuristic - opposingPoke.getStats().getHitPoints().getBase();
                btotal = btotal + opposingPoke.getStats().getHitPoints().getBase();
                if(!opposingPoke.getStatus().getType().equals(PokeStatusType.NOSTATUS)){
                    heuristic = heuristic + 5;
                }
            }
			System.out.println("A side heuristic: " + heuristic);
			System.out.println("A total hp: " + atotal + " B total hp: " + btotal);
		}
		else{
			for(int i = 0; i < this.playerB.currentPokemon; i++){
			    //Should be flipped because player B wants to minimize
				//set pokelist to enemy's pokemon
				pokelist = this.playerB.getParty();
				//subtract from heuristic for their hp
                Pokemon thisPoke = pokelist.get(i);
                heuristic = heuristic - thisPoke.getStats().getHitPoints().getBase();
                if(!thisPoke.getStatus().getType().equals(PokeStatusType.NOSTATUS)) {
                    heuristic = heuristic + 5;
                }
			}
			for(int j = 0; j< this.playerA.currentPokemon; j++) {
                Pokemon opposingPoke = (Pokemon) this.playerA.getParty().get(j);
                heuristic = heuristic + opposingPoke.getStats().getHitPoints().getBase();
                if(!opposingPoke.getStatus().getType().equals(PokeStatusType.NOSTATUS)){
                    heuristic = heuristic - 5;
                }
            }
			System.out.println("B side heuristic: " + heuristic);
		}*/
		
		
			for (Pokemon p: this.playerA.pokemon)
			{
				if (p.getName() == this.currentA.getName())
					aSum += this.currentA.getStats().getHitPoints().getBase();
				else
					aSum += p.getStats().getHitPoints().getBase();
				
				if (p.getStatus().getType() != PokeStatusType.NOSTATUS)
					aSum -= 1;
			}
			
		
			for (Pokemon p: this.playerB.pokemon)
			{
				if (p.getName() == this.currentB.getName())
					bSum += this.currentB.getStats().getHitPoints().getBase();
				else
					bSum += p.getStats().getHitPoints().getBase();
				
				if (p.getStatus().getType() != PokeStatusType.NOSTATUS)
					bSum -= 1;
				
			}
			
			if (!isPlayerAGoing)
				heuristic = aSum;
			else
				heuristic = bSum;
			
		return heuristic;
	}

	public Pokemon getCurrentA() {
		return currentA;
	}

	public void setCurrentA(Pokemon A) {
		this.currentA = A;
	}

	public Pokemon getCurrentB() {
		return currentB;
	}
	
	public void setCurrentB(Pokemon B){
		this.currentB = B;
	}
	public PokeMove getLastMove(){
		return this.lastMove;
	}

	public void setInheritedHeuristic(Integer alpha) {
		this.alpha = alpha;
		
	}
	public Integer getInheritedHeuristic()
	{
		return alpha;
	}
}
