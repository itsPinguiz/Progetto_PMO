/********************
 * IMPORT AND PACKAGE
 *******************/

package Actors.Person;

import Actors.Actions.PlayerActions;
import Place.Place;

/*********************************
 * COMMON INTERFACE FOR ALL PEOPLE
 ********************************/
public interface Person{

    PlayerActions getActions();
    void          setActions(PlayerActions newAction);
    Place         getPlace();
    void          setPlace(Place actualPlace);
    
}