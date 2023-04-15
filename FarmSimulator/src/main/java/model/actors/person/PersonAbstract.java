/********************
 * IMPORT AND PACKAGE
 *******************/

package model.actors.person;

import model.actors.actions.PlayerActions;
import model.place.Place;

/*****************
 * PERSON ABSTRACT
 ****************/
public abstract class PersonAbstract implements Person {
    
    protected Place place;
    protected PlayerActions personAction;

    public Place getPlace(){
        return this.place;
    }
    public void setPlace(Place actualPlace){
        this.place = actualPlace;
    }
    public void setActions(PlayerActions newAction){
        this.personAction = newAction;
    }
    public PlayerActions getActions(){
        return this.personAction;
    }

}
