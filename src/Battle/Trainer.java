package Battle;

import Pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class Trainer {

    ArrayList<Pokemon> pokemon;
    int currentPokemon;

    public Trainer(ArrayList<Pokemon> party){
        this.pokemon = party;
        this.currentPokemon = 0;
    }

    public boolean valid() {
        if(pokemon.size() > 0 && pokemon.size() <= 6){
            for(Pokemon poke : pokemon) {
                if(!poke.valid()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public ArrayList getParty() {
        return this.pokemon;
    }
    //Opportunity for AI here
    //TODO
    public Pokemon nextPokemon(){
        for(int i = 0; i < pokemon.size(); i++) {
            Pokemon poke = pokemon.get(i);
            System.out.println();
            System.out.println();
            System.out.println(poke.getName() + "   " + poke.getStats().getHitPoints().getBase());

            if(poke.getStats().getHitPoints().getBase() > 0) {
                this.currentPokemon = i;
                return poke; //SELECT SMARTER
            }
        }
        return null;
    }

    public Trainer setParty(int index, Pokemon poke){
        this.pokemon.set(index, poke);

        return this;
    }

}
