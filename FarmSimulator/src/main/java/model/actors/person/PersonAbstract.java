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
    public void setActions(PlayerActions newAction){
        this.personAction = newAction;
    }
    public PlayerActions getActions(){
        return this.personAction;
    }

    public Role getRole(){
        return this.role;
    }

}
