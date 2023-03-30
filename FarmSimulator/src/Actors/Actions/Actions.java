package Actors.Actions;

import java.io.Serializable;
import java.util.Set;
import Actors.Actions.ActionsManager.Action;

interface Actions extends Serializable{ 
    void updateActions(Set<Action> actions, boolean add);
    Set<Action> getActions();
}
