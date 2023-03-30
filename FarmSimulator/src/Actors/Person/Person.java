/********************
 * IMPORT AND PACKAGE
 *******************/

package Actors.Person;

import java.io.Serializable;

import Actors.Actions.PlayerActions;
import Place.Place;

/*********************************
 * COMMON INTERFACE FOR ALL PEOPLE
 ********************************/
public interface Person extends Serializable{

    PlayerActions getActions();
    void          setActions(PlayerActions newAction);
    Place         getPlace();
    void          setPlace(Place actualPlace);
    
}