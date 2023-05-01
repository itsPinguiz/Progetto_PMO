/********************
 * IMPORT AND PACKAGE
 *******************/

package model.actors.person;

import java.util.HashSet;

import model.actors.actions.PlayerActions;
import model.place.Place;
import model.place.Places;

/*****************
 * PERSON ABSTRACT
 ****************/
public abstract class PersonAbstract implements Person {
    
    protected Place place;
    protected PlayerActions<? extends Person> personAction;
    protected HashSet<Places> accessiblePlaces;
    protected Role role;

    public enum Role{
        FARMER("Farmer"),
        LANDLORD("Landlord");

        Role(String name)
        {
            this.name = name;
        }

        String name;

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public Place getPlace(){
        return this.place;
    }
    public void setPlace(Place actualPlace){
        this.place = actualPlace;
    }

    public PlayerActions<? extends Person> getActions() {
        return this.personAction;
    }
    

    public HashSet<Places> getAccessiblePlaces(){
        return this.accessiblePlaces;
    }

    public Role getRole(){
        return this.role;
    }

}
