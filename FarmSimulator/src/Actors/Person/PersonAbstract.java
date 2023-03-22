/********************
 * IMPORT AND PACKAGE
 *******************/

package Actors.Person;

import Actors.Actions.PlayerActions;
import Place.Place;

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
