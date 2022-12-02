package Actors.Actions;

import java.util.HashSet;
import java.util.Set;

// DESIGN PATTERN MEDIATOR

public abstract class Actions implements ActionsInt{
    // all actual available actions
    protected Set<String> availableActions;

    protected Actions(){
        this.availableActions = new HashSet<String>();
    }

    public void addAction(String action) {
        /*
         * Method to add action to the 
         * action list
         */
        this.availableActions.add(action);
        
    }

    public void removeAction(String action) {
        /*
         * Method to remove action to the 
         * action list
         */
        this.availableActions.remove(action);
    }

    public Set<String> getActions(){
        /*
         * Method that returns all
         * the possible actions
         */
        return this.availableActions;
    }
    
}
