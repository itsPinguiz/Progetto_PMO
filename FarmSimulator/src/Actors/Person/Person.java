package Actors.Person;

import Actors.Actions.PersonActions;

/**
 * Common interface for all people
 */
public interface Person{

    PersonActions getActions();
    void          setActions(PersonActions newAction);
    Object        getPlace();
    void          setPlace(Object actualPlace);
    
}