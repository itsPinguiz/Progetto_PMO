package Actors.Person;

import Actors.Actions.PersonActions;
import Place.Place;

/**
 * Common interface for all people
 */
public interface Person{

    PersonActions getActions();
    void          setActions(PersonActions newAction);
    Place         getPlace();
    void          setPlace(Object actualPlace);
    
}