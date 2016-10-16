package Examples;

import PokeMove.PokeMove;
import Pokemon.PokeStats.PokeStats;
import Pokemon.PokeStatus.PokeStatus;
import Pokemon.Pokemon;
import Pokemon.PokeType;
import PokeMove.*;

import java.util.ArrayList;

import Error.InvalidModifier;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class Examples {

    public void examples() throws InvalidModifier{
        //Move Sets
        //Charmander Move Set
        ArrayList charmanderMoves = new ArrayList(4);
        charmanderMoves.add(new PMScratch());
        charmanderMoves.add(new PMEmber());
        charmanderMoves.add(new PMGrowl());
        charmanderMoves.add(new PMTailWhip());

        //SquirtleMoves
        ArrayList squirtleMoves = new ArrayList(4);
        squirtleMoves.add(new PMScratch());
        squirtleMoves.add(new PMDefenseCurl());
        squirtleMoves.add(new PMBubble());
        squirtleMoves.add(new PMGrowl());

        //BulbasaurMoves
        ArrayList bulbasaurMoves = new ArrayList(4);
        bulbasaurMoves.add(new PMTailWhip());
        bulbasaurMoves.add(new PMVineWhip());
        bulbasaurMoves.add(new PMSleepPowder());
        bulbasaurMoves.add(new PMParalysisPowder());


        //PikachuMoves
        ArrayList pikachuMoves = new ArrayList(4);
        pikachuMoves.add(new PMThunderShock());
        pikachuMoves.add(new PMTailWhip());
        pikachuMoves.add(new PMDefenseCurl());
        pikachuMoves.add(new PMGrowl());

        //Stats

        //CharmanderStats
        PokeStats charmanderStats = new PokeStats(52, 43, 60, 50, 65, 45);
        PokeStats squirtleStats = new PokeStats(48, 65, 50, 64, 43, 45);
        PokeStats bulbasaurStats = new PokeStats(49, 49, 65, 65, 45, 45);
        PokeStats pikachuStats = new PokeStats(55, 30, 50, 40, 90, 45);

        //STATUS
        //Regular
        PokeStatus noStatus = new PokeStatus();

        //Pokemon
        Pokemon charmander = new Pokemon(PokeType.FIRE, "Charmander", charmanderMoves, noStatus, charmanderStats);
        Pokemon squirtle = new Pokemon(PokeType.WATER, "Squirtle", squirtleMoves, noStatus, squirtleStats);
        Pokemon bulbasaur = new Pokemon(PokeType.GRASS, "Bulbasaur", bulbasaurMoves, noStatus, bulbasaurStats);
        Pokemon pikachu = new Pokemon(PokeType.ELECTRIC, "Pikachu", pikachuMoves, noStatus, pikachuStats);
    }


    public Pokemon getCharmander() throws InvalidModifier{
        ArrayList charmanderMoves = new ArrayList(4);
        charmanderMoves.add(new PMScratch());
        charmanderMoves.add(new PMEmber());
        charmanderMoves.add(new PMGrowl());
        charmanderMoves.add(new PMTailWhip());

        PokeStats charmanderStats = new PokeStats(52, 43, 60, 50, 65, 45);

        PokeStatus noStatus = new PokeStatus();

        Pokemon charmander = new Pokemon(PokeType.FIRE, "Charmander", charmanderMoves, noStatus, charmanderStats);

        return charmander;
    }

    public Pokemon getSquirtle() throws InvalidModifier {
        //SquirtleMoves
        ArrayList squirtleMoves = new ArrayList(4);
        squirtleMoves.add(new PMScratch());
        squirtleMoves.add(new PMDefenseCurl());
        squirtleMoves.add(new PMBubble());
        squirtleMoves.add(new PMGrowl());

        PokeStats squirtleStats = new PokeStats(48, 65, 50, 64, 43, 45);

        PokeStatus noStatus = new PokeStatus();

        Pokemon squirtle = new Pokemon(PokeType.WATER, "Squirtle", squirtleMoves, noStatus, squirtleStats);

        return squirtle;
    }

    public Pokemon getBulbasaur() throws InvalidModifier {
        //BulbasaurMoves
        ArrayList bulbasaurMoves = new ArrayList(4);
        bulbasaurMoves.add(new PMTailWhip());
        bulbasaurMoves.add(new PMVineWhip());
        bulbasaurMoves.add(new PMSleepPowder());
        bulbasaurMoves.add(new PMParalysisPowder());

        PokeStatus noStatus = new PokeStatus();

        PokeStats bulbasaurStats = new PokeStats(49, 49, 65, 65, 45, 45);

        Pokemon bulbasaur = new Pokemon(PokeType.GRASS, "Bulbasaur", bulbasaurMoves, noStatus, bulbasaurStats);

        return bulbasaur;
    }

    public Pokemon getPikachu() throws InvalidModifier {
        //PikachuMoves
        ArrayList pikachuMoves = new ArrayList(4);
        pikachuMoves.add(new PMThunderShock());
        pikachuMoves.add(new PMTailWhip());
        pikachuMoves.add(new PMDefenseCurl());
        pikachuMoves.add(new PMGrowl());

        PokeStats pikachuStats = new PokeStats(55, 30, 50, 40, 90, 45);

        PokeStatus noStatus = new PokeStatus();
        Pokemon pikachu = new Pokemon(PokeType.ELECTRIC, "Pikachu", pikachuMoves, noStatus, pikachuStats);

        return pikachu;

    }


}
