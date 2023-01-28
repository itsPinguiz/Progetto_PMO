package Place;

import Actors.Actions.PlaceActions;
import Place.Land.enu.Places;

public abstract class Place {
    // General class of places
    protected PlaceActions actions;
    protected Places place;

    public PlaceActions getActions(){
        /*
         *  Returns the actions of the land
         */
        return this.actions;
    }
}
