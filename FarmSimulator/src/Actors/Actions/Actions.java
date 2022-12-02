package Actors.Actions;

import java.util.HashMap;
import java.util.Map;

// DESIGN PATTERN MEDIATOR

public abstract class Actions implements ActionsInt{
    // all actual available actions
    protected Map<String,String> availableActions;

    protected Actions(){
        this.availableActions = new HashMap<String,String>();
    }

    public void addAction(String k,String v) {
        /*
         * Method to add action to the 
         * action list
         */
        this.availableActions.put(k,v);
        
    }

    public void removeAction(String k) {
        /*
         * Method to remove action to the 
         * action list
         */
        this.availableActions.remove(k);
    }

    public Map<String,String> getActions(){
        /*
         * Method that returns all
         * the possible actions
         */
        return this.availableActions;
    }
    
}
