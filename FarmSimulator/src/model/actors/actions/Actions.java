package model.actors.actions;

import java.io.Serializable;
import java.util.Set;

import model.actors.actions.ActionsManager.Action;

interface Actions extends Serializable{ 
    void updateActions(Set<Action> actions, boolean add);
    Set<Action> getActions();
}
