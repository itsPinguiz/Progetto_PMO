package Place;

import Actors.Actions.PlaceActions;

public abstract class Place {
    // General class of places
    protected PlaceActions actions;
    protected Places type; // the type of the place

    public PlaceActions getActions(){
        /*
         *  Returns the actions of the land
         */
        return this.actions;
    }

    public Places getType() {
        /*
         * Returns the type of the
         * land  
         */
        return this.type;
    }
}
