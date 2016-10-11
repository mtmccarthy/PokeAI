package Pokemon;

import PokeMove.PokeMove;
import Pokemon.PokeEffect.PokeEffect;
import Pokemon.PokeStats.PokeStat;
import Pokemon.PokeStats.PokeStatType;
import Pokemon.PokeStats.PokeStats;
import Pokemon.PokeStatus.PokeStatus;
import Pokemon.PokeStatus.PokeStatusType;

import java.util.ArrayList;
import java.util.Random;

import Error.InvalidPokemonError;
import Error.InvalidModifier;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class Pokemon {

    PokeType type;
    ArrayList<PokeMove> moves;
    String name;
    PokeStatus status;
    PokeStats stats;

    int index;

    Random ran;

    public Pokemon(PokeType type, String name) {
        this.type = type;
        this.name = name;
        this.moves = new ArrayList<>();
        this.ran = new Random();
    }
    public Pokemon(PokeType type, String name, ArrayList moves, PokeStatus status, PokeStats stats) {
        this.type = type;
        this.name = name;
        this.moves = moves;
        this.status = status;
        this.stats = stats;
        this.ran = new Random();
    }

    public Pokemon(PokeType type, String name, ArrayList moves, PokeStatus status, PokeStats stats, int index) {
        this.type = type;
        this.name = name;
        this.moves = moves;
        this.status = status;
        this.stats = stats;
        this.ran = new Random();
        this.index = index;
    }

    public boolean valid(){
        return true;
    }

    public Pokemon normalSleep() throws InvalidPokemonError {
        int counter = this.getRan().nextInt(6); //SHOULD THIS BE 6?
        PokeStatus newStatus = new PokeStatus(PokeStatusType.SLEEP, counter);

        if(this.status.getType().equals(PokeStatusType.SLEEP)){
            return this;
        }
        else if(!this.status.getType().equals(PokeStatusType.NOSTATUS)){
            System.out.println(this.getName() + " would have fell asleep. But it already has a status.");
            return this;
        }
        else {
            System.out.println(this.getName() + " is fast asleep.");
            return this.addStatus(this, newStatus);
        }
    }

    public Pokemon addStatus(Pokemon poke, PokeStatus status) throws InvalidPokemonError{
        Pokemon newPoke = new Pokemon(poke.type, poke.name, poke.moves, status, poke.stats);
        if(!newPoke.valid()) {
            throw new InvalidPokemonError();
        }
        else {
            return newPoke;
        }
    }

    public Pokemon paralyze() throws InvalidPokemonError{
        int counter = -1; //Indefinite
        PokeStatus newStatus = new PokeStatus(PokeStatusType.PARALYSIS, counter);

        if(this.status.getType().equals(PokeStatusType.PARALYSIS)){
            return this;
        }
        else if(!this.status.getType().equals(PokeStatusType.NOSTATUS)){
            System.out.println(this.getName() + " would have been paralyzed. But it already has a status.");
            return this;
        }
        else {
            System.out.println(this.getName() + " is paralyzed.");
            return this.addStatus(this, newStatus);
        }


    }

    public Pokemon burn() throws InvalidPokemonError {
        int counter = -1;
        PokeStatus newStatus = new PokeStatus(PokeStatusType.BURN, counter);

        if(this.status.getType().equals(PokeStatusType.BURN)){
            return this;
        }
        else if(!this.status.getType().equals(PokeStatusType.NOSTATUS)){
            System.out.println(this.getName() + " would have been burned. But it already has a status.");
            return this;
        }
        else {
            System.out.println(this.getName() + " is burned.");
            return this.addStatus(this, newStatus);
        }
    }

    public Pokemon increaseStat(int scale, PokeStatType type) throws InvalidPokemonError, InvalidModifier {
        Pokemon newPokemon = new Pokemon(this.type, this.name, this.moves, this.status, this.stats.increaseStat(type, scale));

        return newPokemon;
    }

    public Pokemon decreaseStat(int scale, PokeStatType type) throws InvalidPokemonError, InvalidModifier {
        Pokemon newPokemon = new Pokemon(this.type, this.name, this.moves, this.status, this.stats.decreaseStat(type, scale));

        return newPokemon;
    }

    //Opportunity for AI here
    public PokeMove chooseMove(Pokemon defender){
        int nextMove = this.ran.nextInt(4);

        return this.moves.get(nextMove);
    }

    public AttackerDefenderPair attack(Pokemon defender, PokeMove move) throws InvalidPokemonError, InvalidModifier{

        if(this.status.getType().equals(PokeStatusType.PARALYSIS)){
            int paralyzed = this.ran.nextInt(4);
            if(paralyzed == 0){
                System.out.println(this.getName() + " is paralyzed. It can't move.");
                return new AttackerDefenderPair(this, defender);
            }
        }
        else if(this.status.getType().equals(PokeStatusType.SLEEP)){
            if(this.status.getCounter() <= 0) {
                System.out.println(this.getName() + " woke up!");
                PokeStatus newStatus = new PokeStatus();
                this.status = newStatus;
            }
            else {
                System.out.println(this.getName() + " is asleep.");
                PokeStatus newStatus = new PokeStatus(PokeStatusType.SLEEP, this.status.getCounter() - 1);
                this.status = newStatus;
                return new AttackerDefenderPair(this, defender);
            }
        }

        PokeEffect effect = move.getEffect();


        //Below deals with damage portion of an attack
        PokeStat currentHP = defender.stats.getHitPoints();
        double modifer = defender.getModifier(move.getType());//TODO
        //http://bulbapedia.bulbagarden.net/wiki/Damage
        //Set level constant to 5 for simplicity
        int damage;
        if(move.getDamage() == 0){
            damage = 0;
        }
        else{
            int level = 5;
            double interMediateDamage1 = (2*level+10)/250;
            double interMediateDamage2 = (this.stats.getAttack().getModifiedStat()/defender.stats.getDefense().getModifiedStat())*move.getDamage();
            double interMediateDamage3 = (interMediateDamage1*interMediateDamage2 + 2)*modifer;
            //Damage must be an integer
            damage = (int) interMediateDamage3;
            if(damage == 0){
                damage = 1;
            }
        }

        System.out.println(this.getName()+" caused " + damage + " damage");
        PokeStat newHP = currentHP.damage(currentHP.getType(), damage);//TODO
        System.out.println(defender.getName() + " now has " + newHP.getBase() +" health");
        PokeStats newDefenderStats =
                new PokeStats(defender.stats.getAttack(), defender.stats.getDefense(), defender.stats.getSpecialAttack(),
                defender.stats.getSpecialDefense(), defender.stats.getSpeed(), newHP);
        Pokemon newDefender = new Pokemon(defender.type, defender.name, defender.moves, defender.status, newDefenderStats);


        //Below deals with an additional effect of a move
        AttackerDefenderPair pair = effect.effect(this, newDefender);

        return pair;
    }


    public double getModifier(PokeType typeOfMove){
        PokeType defenderType = this.type;

        return defenderType.getModifier(typeOfMove);
    }

    public PokeStats getStats(){
        return this.stats;
    }

    public String getName(){
        return this.name;
    }

    public Random getRan(){
        return this.ran;
    }

    public int getIndex(){return this.index;}
    public void setIndex(int index){
        this.index = index;
    }
}
