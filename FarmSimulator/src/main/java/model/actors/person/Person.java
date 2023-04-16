/********************
 * IMPORT AND PACKAGE
 *******************/

package model.actors.person;

import java.io.Serializable;

import model.actors.actions.PlayerActions;
import model.actors.person.PersonAbstract.Role;
import model.place.Place;

/*********************************
 * COMMON INTERFACE FOR ALL PEOPLE
 ********************************/
public interface Person extends Serializable{

    PlayerActions getActions();
    void          setActions(PlayerActions newAction);
    Place         getPlace();
    void          setPlace(Place actualPlace);
    Role          getRole();
    
}