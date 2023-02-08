package Actors.Actions;

import java.util.HashSet;
import java.util.Set;

import Actors.Person.Person;

// DESIGN PATTERN MEDIATOR

public abstract class ActionsManager implements Actions{
    // all actual available actions
    protected Set<Action> availableActions;

    // constructor
    protected ActionsManager(){
        this.availableActions = new HashSet<>();

    }

    public enum Action {
        /*
         * All possible actions
         */
        PLANT,
        WATER,
        FERTILIZE,
        HARVEST,
        PLOW,
        WATER_ALL,
        FERTILIZE_ALL,
        HARVEST_ALL,
        PLOW_ALL,
        ADD_ANIMAL,
        REMOVE_ANIMAL,
        GET_ALL_RESOURCES,
        GET_ITEM,
        MOVE_ITEM
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

    
}
