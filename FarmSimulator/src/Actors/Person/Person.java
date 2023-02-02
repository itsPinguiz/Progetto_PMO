package Actors.Person;

import Actors.Actions.PlayerActions;
import Place.Place;

/**
 * Common interface for all people
 */
public interface Person{

    PlayerActions getActions();
    void          setActions(PlayerActions newAction);
    Place         getPlace();
    void          setPlace(Place actualPlace);
    
}