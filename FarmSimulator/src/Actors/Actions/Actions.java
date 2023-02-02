package Actors.Actions;

import java.util.HashSet;
import java.util.Set;

interface Actions { 
    void addAction(String action);
    void addActions(Set<String> actions);
    void removeAction(String action);
    void removeActions(Set<String> action);
    Set<String> getActions();
}
