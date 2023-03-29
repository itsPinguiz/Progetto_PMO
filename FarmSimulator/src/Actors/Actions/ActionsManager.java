package Actors.Actions;

import java.util.HashSet;
import java.util.Set;
// DESIGN PATTERN MEDIATOR

public abstract class ActionsManager implements Actions{
    // attributes
    protected Set<Action> availableActions;

    // constructor
    protected ActionsManager(){
        this.availableActions = new HashSet<>();

    }

    public enum Action {
        /*
         * All possible actions
         */
        PLANT("Plant"),
        WATER("Water"),
        FERTILIZE("Fertilize"),
        HARVEST("Harvest"),
        PLOW("Plow"),
        WATER_ALL("Water All"),
        FERTILIZE_ALL("Fertilize All"),
        HARVEST_ALL("Harvest All"),
        PLOW_ALL("Plow All"),
        ADD_ANIMAL("Add Animal"),
        REMOVE_ANIMAL("Remove Animal"),
        GET_ALL_RESOURCES("Get All Resources"),
        GET_ITEM("Get Item"),
        MOVE_ITEM("Move Item");

        private String name;

        Action(String name) {
            this.name = name;
        }
    
        public String getName() {
            return name;
        }
    
        @Override
        public String toString() {
            return getName();
        }
    }

    public void updateActions(Set<Action> actions, boolean add) {
        /*
         * Adds or removes actions from available actions
         */
        if (add) {
            this.availableActions.addAll(actions);
        } else {
            this.availableActions.removeAll(actions);
        }
    }

    public Set<Action> getActions(){
        /*
         * Method that returns all
         * the possible actions
         */
        return this.availableActions;
    }  

    public void resetActions(){
        /*
         * Resets the available actions
         */
        this.availableActions = null;
    }

    
}
