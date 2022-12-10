package Actors.Actions;

import java.util.concurrent.atomic.AtomicInteger;

import Actors.Person.Person;

public class PersonActions extends GameActions{
    protected final Person person;
    protected Object place; //TODO replace object with place
    //private final Person place;
    // index for the item to do the action on
    public static AtomicInteger itemIndex;

    protected PersonActions(Person person){
        super();
        this.person = person;
    }

    public void executeAction(String s) {
        /*
         * Method to find a method to execute
         */
        this.place = this.person.getPlace();

        // find the method and execute it
        if (super.availableActions.contains(s)){
            try {
            this.getClass().getMethod(s).invoke(null);
            System.out.println(s + " executed.");
            } catch (Exception e) {
                System.out.println("Wasn't able to execute action due to " + e);
            } 
        } else {
            System.out.println("Action is not available.");
        }
    }
}
