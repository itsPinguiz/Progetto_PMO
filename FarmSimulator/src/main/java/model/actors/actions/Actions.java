package model.actors.actions;

import java.io.Serializable;
import java.util.Set;

import model.actors.actions.ActionsManager.Action;

/**
 * Interface that represents the action manager
 */
interface Actions extends Serializable{ 
    /**
     * Method that updates the available actions
     * @param actions actions to add or remove
     * @param add true if the actions have to be added, false if they have to be removed
     */
    void updateActions(Set<Action> actions, boolean add);

    /**
     * Method that returns the available actions
     * @return available actions
     */
    Set<Action> getAvailableActions();

    /**
     * Method that resets the available actions
     */
    void resetActions();
}
