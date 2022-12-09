package Actors.Person;

import Actors.Actions.PersonActions;

public abstract class PersonAbstract implements Person {
    
    protected Object place = new Object();
    protected PersonActions personAction;

    public Object getPlace(){
        return this.place;
    }
    public void setPlace(Object actualPlace){
        this.place = actualPlace;
    }
    public void setActions(PersonActions newAction){
        this.personAction = newAction;
    }
    public PersonActions getActions(){
        return this.personAction;
    }

}
