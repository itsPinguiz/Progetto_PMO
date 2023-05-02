package model.place;

import java.io.Serializable;

import model.actors.actions.placeActions.PlaceActions;

/**
 * General class of places
 */
public abstract class Place implements Serializable{
    /**
     * Attributes
     */
    protected PlaceActions actions;
    protected Places type; // the type of the place

    /**
     * Returns the actions of the land
     * @return PlaceActions
     */
    public PlaceActions getActions(){
        
        return this.actions;
    }

    /*
     * Returns the type of the
     * land  
     * @return Places 
     */
    public Places getType() {
        return this.type;
    }
}
