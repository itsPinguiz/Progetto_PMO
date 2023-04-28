package model.place.land.chunks;

import model.actors.actions.PlaceActions;

public interface Chunk {    
    public void update();
    public void resetActions();
    public PlaceActions getActions();
}
