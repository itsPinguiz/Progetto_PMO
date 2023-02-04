package Actors.Actions;

import java.util.Set;
import Actors.Actions.ActionsManager.Action;

interface Actions { 
    void updateActions(Set<Action> actions, boolean add);
    Set<Action> getActions();
}
