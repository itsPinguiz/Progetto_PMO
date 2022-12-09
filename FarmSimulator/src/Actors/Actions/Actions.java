package Actors.Actions;

import java.util.Set;

interface Actions { 
    void addAction(String action);
    void removeAction(String action);
    Set<String> getActions();
}
