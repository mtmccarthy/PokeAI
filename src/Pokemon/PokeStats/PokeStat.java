package Pokemon.PokeStats;

import Error.InvalidModifier;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PokeStat {

    PokeStatType type;
    int base;
    int counter;
    double multiplier;

    public PokeStat(int statNum, PokeStatType type) {
        this.type = type;
        this.base = statNum;
        this.counter = 0;
        this.multiplier = 1;
    }
    public PokeStat(int statNum, PokeStatType type, int counter) {
        this.type = type;
        this.base = statNum;
        this.counter = counter;
        if(counter == 0) {
            this.multiplier = 1;
        }
        else if(counter == 1) {
            this.multiplier = 1.5;
        }
        else if(counter == 2) {
            this.multiplier = 2;
        }
        else if(counter == 3) {
            this.multiplier = 2.5;
        }
        else if(counter == 4) {
            this.multiplier = 3;
        }
        else if(counter == 5) {
            this.multiplier = 3.5;
        }
        else if(counter == 6) {
            this.multiplier = 4;
        }
        else if(counter == -1) {
            this.multiplier = 2/3;
        }
        else if(counter == -2) {
            this.multiplier = 2/4;
        }
        else if(counter == -3) {
            this.multiplier = 2/5;
        }
        else if(counter == -4) {
            this.multiplier = 2/6;
        }
        else if(counter == -5) {
            this.multiplier = 2/7;
        }
        else if(counter == -6) {
            this.multiplier = 2/8;
        }
    }

    public PokeStat incrementCounter(int inc) throws InvalidModifier{
        if(this.type.equals(PokeStatType.HITPOINTS)){
            throw new InvalidModifier();
        }
        else if(this.counter == 6) {
            System.out.println("The "+type+" is maxed out.");
            return new PokeStat(this.base, this.type, this.counter);
        }
        int newCounter = this.counter + inc;
        if(newCounter > 6) {
            System.out.println("The "+ type+ " is now maxed out");
            return new PokeStat(this.base, this.type, 6);
        }
        else {
            return new PokeStat(this.base, this.type, newCounter);
        }

    }
    public PokeStat decrementCounter(int dec) throws InvalidModifier{
        if(this.type.equals(PokeStatType.HITPOINTS)){
            throw new InvalidModifier();
        }
        else if(this.counter == -6) {
            System.out.println("The " + type + " is as low as it can go.");
            return new PokeStat(this.base, this.type, this.counter);
        }
        int newCounter = this.counter - dec;
        if(newCounter < -6) {
            System.out.println("The " + type + " is now as low is it can go.");
            return new PokeStat(this.base, this.type, -6);
        }
        else {
            return new PokeStat(this.base, this.type, newCounter);
        }
    }

    public PokeStat damage(PokeStatType type, int dam) throws InvalidModifier{
        if(!type.equals(PokeStatType.HITPOINTS)){
            throw new InvalidModifier();
        }
        else {
            int newHP = this.base - dam;
            return new PokeStat(newHP, PokeStatType.HITPOINTS);
        }
    }

    public PokeStatType getType(){
        return this.type;
    }
    public int getBase(){
        return this.base;
    }
    public int getCounter(){
        return this.counter;
    }

    public double getModifer(){
        //Modifier is kept track of in the constructor
        return this.multiplier;
    }

    public double getModifiedStat(){

        return this.base*this.multiplier;
    }

}
