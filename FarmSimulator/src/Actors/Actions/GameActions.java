package Actors.Actions;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

// DESIGN PATTERN MEDIATOR

public abstract class GameActions implements Actions{
    // all actual available actions
    protected Set<String> availableActions;

    protected GameActions(){
        this.availableActions = new HashSet<String>();
    }

    public void addAction(String action) {
        /*
         * Method to add action to the 
         * action list
         */
        this.availableActions.add(action);   
    }

    public void addAction(Set<String> action) {
        /*
         * Method to add action to the 
         * action list
         */
        this.availableActions.addAll(action);   
    }

    public void removeAction(String action) {
        /*
         * Method to remove action to the 
         * action list
         */
        this.availableActions.remove(action);
    }

    
    public void removeAction(Set<String> action) {
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
