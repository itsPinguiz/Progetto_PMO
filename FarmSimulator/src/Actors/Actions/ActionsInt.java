package Actors.Actions;

import java.util.Set;

interface ActionsInt { 
    void addAction(String action);
    void removeAction(String action);
    Set<String> getActions();
}
