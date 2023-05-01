/********************
 * IMPORT AND PACKAGE
 *******************/

package model.actors.person;

import java.io.Serializable;
import java.util.HashSet;

import model.actors.actions.PlayerActions;
import model.actors.person.PersonAbstract.Role;
import model.place.Place;
import model.place.Places;

/*********************************
 * COMMON INTERFACE FOR ALL PEOPLE
 ********************************/
public interface Person extends Serializable{
    PlayerActions<? extends Person> getActions();
    public HashSet<Places> getAccessiblePlaces();
    Place         getPlace();
    void          setPlace(Place actualPlace);
    Role          getRole();
}
