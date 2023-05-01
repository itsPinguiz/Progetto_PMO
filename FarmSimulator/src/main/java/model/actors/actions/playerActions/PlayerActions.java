package model.actors.actions.playerActions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

import model.actors.actions.ActionArguments;
import model.actors.actions.ActionsManager;
import model.actors.person.Farmer;
import model.actors.person.Landlord;
import model.actors.person.Person;
import model.actors.person.PersonAbstract.Role;
import model.exceptions.CustomExceptions.*;
import model.item.Item;
import model.item.tools.AbstractTool;
import model.place.GameMap;
import model.place.Place;
import model.place.Places;
import model.place.land.LandAbstract;

public abstract class PlayerActions<T extends Person> extends ActionsManager{
    // attributes
    protected T person;
    protected ActionArguments<Place,?,GameMap> argument;
    private HashMap<Role,Class<? extends Person>> roleClass;

    // constructor    
    public PlayerActions(T person){
        this.person = person;
        roleClass = new HashMap<>(){
            {
                put(Role.FARMER, Farmer.class);
                put(Role.LANDLORD, Landlord.class);
            }
        };
    }

    public <V> void executeAction(Action s,ActionArguments<Place,V,GameMap> argument) throws Exception{
        /*
        * Method to find a method to execute
        */

        // find the desired method
        Method method = getMethodByName(s.name().toLowerCase());
        this.argument = argument;

        // execute the method if it exists and is available
        if (method != null && this.availableActions.contains(s)) {
            person.getActions().updateActions(person.getPlace().getActions().getAvailableActions(), false);
            method.invoke(person.getActions());        
            person.getActions().updateActions(person.getPlace().getActions().getAvailableActions(), true);
        } else {
            throw new ActionNotAvailableException(s, this.availableActions);
        }
        argument = null;
    }

    private  Method getMethodByName(String methodName) {
        /*
         * Method to get a method by its name
         */
        // search the method in the current class
        Method method = Arrays.stream(getClass().getDeclaredMethods())
                              .filter(m -> m.getName().equals(methodName.toLowerCase()))
                              .findFirst()
                              .orElse(null);

        if (method == null) {
            // if method was not found, look for it in the role class
            method = Arrays.stream(roleClass.get(person.getRole()).getDeclaredMethods())
                           .filter(m -> m.getName().equals(methodName.toLowerCase()))
                           .findFirst()
                           .orElse(null);
            }
        return method;
    }

    public void enter(Place p) throws PlaceNotAvailableException {
        /*
         * Method to change actions when
         * an actors enters somewhere
         */
        if (this.person.getAccessiblePlaces().contains(p.getType())){
            if (person.getPlace() != null)
                leave();
            this.person.setPlace(p);
            // the landlord cannot move items
            if (!(person instanceof Landlord && p.getType() == Places.BARN)){
                this.person.getActions().updateActions(person.getPlace().getActions().getAvailableActions(), true);
            }
        } else throw new PlaceNotAvailableException(p, this.person.getAccessiblePlaces());
    }

    public void leave() {
        /*
         * Method to change actions when
         * an actors leaves from somewhere
         */
        if (person.getPlace()!= null){
            person.getActions().updateActions(person.getPlace().getActions().getAvailableActions(), false);
        }
        person.setPlace(null);
    }

    protected void doAll(Action action) throws IllegalAccessException,
                                             IllegalArgumentException,
                                             InvocationTargetException,
                                             SecurityException,
                                             ActionNotAvailableException{
        /*
        * Method to repeat the same action on all chunks in a land
        */                                                                            
        ((LandAbstract)argument.getArg1()).getElements().stream()
            .filter(chunk -> chunk.getActions().getAvailableActions().contains(action))
            .forEach(chunk -> {
                try {
                    this.argument.setArg1(chunk);
                    this.getMethodByName((action.toString().toLowerCase()).replace(' ', '_')).invoke(this);
                } catch (IllegalAccessException | 
                            IllegalArgumentException | 
                            InvocationTargetException | 
                            SecurityException  e) {
                    e.printStackTrace();
                }
        });
    }


    


    protected boolean damageTool(Item tool) throws NoItemFoundException,
                                                 NotEnoughItemsException{
        /*
         * Use an item and destroy it if it's worn out
         */
        Farmer f = (Farmer)this.person;

        if (tool != null && tool.getStatus() > 0){
            ((AbstractTool)tool).useTool();
            if (tool.getStatus() == 0){
                f.getInventory().removeItem(tool, 1);
            }
            return true;
        }
        return false;
    }


}
