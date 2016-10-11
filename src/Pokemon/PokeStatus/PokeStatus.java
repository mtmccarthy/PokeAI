package Pokemon.PokeStatus;

import Pokemon.PokeStatus.PokeStatusType;

/**
 * Created by mtmccarthy on 10/10/16.
 */
public class PokeStatus {


    /**
     * How much longer the status is valid for this pokemon.
     */
    int counter;
    /**
     * Type of status. Sleep, parlysis, poison etc.
     */
    PokeStatusType type;

    public PokeStatus(PokeStatusType type, int counter){
        this.type = type;
        this.counter = counter;
    }
    public PokeStatus() {
        this.type = PokeStatusType.NOSTATUS;
        this.counter = 0;
    }

    public int getCounter(){
        return this.counter;
    }
    public PokeStatusType getType(){
        return this.type;
    }
}
