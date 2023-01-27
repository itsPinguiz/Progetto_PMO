package Actors.Person;

import Actors.Actions.PersonActions;
import Place.Place;

public abstract class PersonAbstract implements Person {
    
    protected Place place = new Place();
    protected PersonActions personAction;

    public Place getPlace(){
        return this.place;
    }
    public void setPlace(Place actualPlace){
        this.place = actualPlace;
    }
    public void setActions(PersonActions newAction){
        this.personAction = newAction;
    }
    public PersonActions getActions(){
        return this.personAction;
    }

}
