package model.place.land.chunks;

import model.actors.actions.placeActions.PlaceActions;
import model.place.Place;

public abstract class Chunk extends Place{    
    public void update(){
        throw new UnsupportedOperationException();
    }
    public void resetActions(){
        throw new UnsupportedOperationException();
    };
    public PlaceActions getActions(){
        return this.actions;
    };
}
