package Actors.Actions;

import Actors.Person.Person;

public class PersonActions extends Actions{
    private final Person person;

    protected PersonActions(Person person){
        super();
        this.person = person;
    }

    public void executeAction(String s) {
        /*
         * Method to find a method to execute
         */
        // find the method and execute it
        try {
           this.getClass().getMethod(s).invoke(null);
           System.out.println(s + " executed.");
        } catch (Exception e) {
            System.out.println("Wasn't able to execute action due to " + e);
        } 
    }

    public void grabItem(){
        /*
         * Method to grab item from
         * the barn
         */
    }

    public void dropItem(){
        /*
         * Method to drop item in
         * the barn
         */
    }
}
