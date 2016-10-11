package Pokemon;

import java.util.ArrayList;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public enum PokeType {

    GRASS, WATER, FIRE, NOTYPE, NORMAL, ELECTRIC;

    private ArrayList superEffective;
    private ArrayList resistant;

    static{
        GRASS.superEffective = new ArrayList();
        GRASS.superEffective.add(FIRE);

        WATER.superEffective = new ArrayList();
        WATER.superEffective.add(GRASS);
        WATER.superEffective.add(ELECTRIC);

        FIRE.superEffective = new ArrayList();
        FIRE.superEffective.add(WATER);

        ELECTRIC.superEffective = new ArrayList();

        GRASS.resistant = new ArrayList();
        GRASS.resistant.add(WATER);

        WATER.resistant = new ArrayList();
        WATER.resistant.add(FIRE);

        FIRE.resistant = new ArrayList();
        FIRE.resistant.add(GRASS);

        ELECTRIC.resistant = new ArrayList();
        ELECTRIC.resistant.add(WATER);
    }

    public double getModifier(PokeType attackingType) {

        if(this.superEffective.contains(attackingType)){
            return 2.0;
        }
        else if(this.resistant.contains(attackingType)){
            return .5;
        }
        else {
            return 1.0;
        }

    }

}
