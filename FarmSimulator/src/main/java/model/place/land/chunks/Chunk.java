package model.place.land.chunks;

import model.actors.actions.placeActions.PlaceActions;
import model.place.Place;

/**
 * Small part of a land that contains one element
 */
public abstract class Chunk extends Place{    
    /**
     * Updates the chunk's status
     * 
     */
    public void update(){
        throw new UnsupportedOperationException();
    }

    /**
     * Resets the actions of the chunk
     * 
     */
    public void resetActions(){
        throw new UnsupportedOperationException();
    };

    /**
     * Returns the actions of the chunk
     * @return PlaceActions
     */
    public PlaceActions getActions(){
        return this.actions;
    };
}
