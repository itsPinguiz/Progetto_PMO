package Actors.Actions;

import java.util.HashSet;
import java.util.Set;

import Actors.Person.Person;

// DESIGN PATTERN MEDIATOR

public abstract class ActionsManager implements Actions{
    // all actual available actions
    protected Set<String> availableActions;

    protected ActionsManager(){
        this.availableActions = new HashSet<String>();

    }

    public void addAction(String action) {
        /*
         * Method to add action to the 
         * action list
         */
        this.availableActions.add(action);   
    }

    public void addActions(Set<String> actions) {
        /*
         * Method to add action to the 
         * action list
         */
        this.availableActions.addAll(actions);   
    }

    public void removeAction(String action) {
        /*
         * Method to remove action to the 
         * action list
         */
        this.availableActions.remove(action);
    }

    
    public void removeActions(Set<String> action) {
        /*
         * Method to remove action to the 
         * action list
         */
        this.availableActions.removeAll(action);
    }

    public Set<String> getActions(){
        /*
         * Method that returns all
         * the possible actions
         */
        return this.availableActions;
    }  

    
}
