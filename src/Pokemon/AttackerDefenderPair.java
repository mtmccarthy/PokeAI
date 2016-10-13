package Pokemon;

import Pokemon.PokeStats.PokeStat;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class AttackerDefenderPair {

    Pokemon attacker;
    Pokemon defender;

    boolean gameOn;

    public AttackerDefenderPair(boolean displayPrompt, Pokemon attacker, Pokemon defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.gameOn = this.isBattleOver(displayPrompt).isGameOn();
    }

    public AttackerDefenderPair(Pokemon attacker, Pokemon defender, boolean gameOn) {
        this.attacker = attacker;
        this.defender = defender;
        this.gameOn = gameOn;
    }

    public Pokemon getAttacker(){
        return this.attacker;
    }
    public Pokemon getDefender(){
        return this.defender;
    }

    public AttackerDefenderPair isBattleOver(boolean displayPrompt) {

        PokeStat HPAttacker = attacker.getStats().getHitPoints();
        PokeStat HPDefender = defender.getStats().getHitPoints();


        if(HPDefender.getBase() <= 0) {
            if(displayPrompt) {
                System.out.println(defender.getName() + " has fainted.");
            }
            return new AttackerDefenderPair(attacker, defender, false);
        }
        else if(HPAttacker.getBase() <= 0) {
            if(displayPrompt) {
                System.out.println(attacker.getName() + " has fainted.");
            }
            return new AttackerDefenderPair(attacker, defender, false);
        }
        else {
            return new AttackerDefenderPair(attacker, defender, true);
        }
    }

    public boolean isGameOn(){
        return this.gameOn;
    }


}
