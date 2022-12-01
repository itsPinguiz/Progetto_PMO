package Actors.Actions;

import java.util.HashSet;
import java.util.Set;

public abstract class Actions implements ActionsInt{
    // all actual available actions
    protected Set<String> availableActions;

    protected Actions(){
        this.availableActions = new HashSet<>();
    }

    public void addAction() {
        /*
         * Method to add action to the 
         * action list
         */
        // TODO 
        
    }

    public void removeAction() {
        /*
         * Method to remove action to the 
         * action list
         */
        // TODO 
    }

    public Set<String> getActions(){
        /*
         * Method that returns all
         * the possible actions
         */
        return this.availableActions;
    }
    
}
