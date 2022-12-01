package Actors.Actions;

import java.util.Map;

interface ActionsInt { 
    void addAction(String k,String v);
    void removeAction(String k);
    Map<String,String> getActions();
}
