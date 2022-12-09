package Actors.Actions;

import Actors.Person.Person;

public class PersonActions extends GameActions{
    private final Person person;
    private Object place; //TODO replace object with place
    //private final Person place;

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
