package model.actors.actions.playerActions;

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
import model.place.land.chunks.Chunk;

/**
 * Class that contains all the actions that a player can perform
 */
public abstract class PlayerActions<T extends Person> extends ActionsManager{
    /**
     * Attributes
     */
    protected T person;
    protected ActionArguments<Place,?,GameMap> argument;
    private HashMap<Role,Class<? extends Person>> roleClass;

    /**
     * Constructor
     * @param person Person
     */  
    public PlayerActions(T person){
        this.person = person;
        roleClass = new HashMap<>(){
            {
                put(Role.FARMER, Farmer.class);
                put(Role.LANDLORD, Landlord.class);
            }
        };
    }

    /**
     * Executes an action if it is available
     * @param <V> Item or LandAbstract
     * @param action Action to execute
     * @param argument Argument for the action
     * @throws Exception
     */
    public <V> void executeAction(Action action,ActionArguments<Place,V,GameMap> argument) throws Exception{
        // find the desired method
        Method method = getMethodByName(action.name().toLowerCase());
        this.argument = argument;

        // execute the method if it exists and is available
        if (method != null && this.availableActions.contains(action)) {
            person.getActions().updateActions(person.getPlace().getActions().getAvailableActions(), false);
            method.invoke(person.getActions());        
            person.getActions().updateActions(person.getPlace().getActions().getAvailableActions(), true);
        } else {
            throw new ActionNotAvailableException(action, this.availableActions);
        }
        argument = null;
    }

    /**
     * Method to get a method by its name
     * @param methodName Name of the method
     * @return 
     */
    private  Method getMethodByName(String methodName) {
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

    /**
     * Method to change actions when an actor enters a new place
     * @param newPlace Place to enter
     * @throws PlaceNotAvailableException Exception thrown when the actor cannot enter the place
     */
    public void enter(Place newPlace) throws PlaceNotAvailableException {
        /*
         * Method to change actions when
         * an actors enters somewhere
         */
        if (this.person.getAccessiblePlaces().contains(newPlace.getType())){
            if (person.getPlace() != null)
                leave();
            this.person.setPlace(newPlace);
            // the landlord cannot move items
            if (!(person instanceof Landlord && newPlace.getType() == Places.BARN)){
                this.person.getActions().updateActions(person.getPlace().getActions().getAvailableActions(), true);
            }
        } else throw new PlaceNotAvailableException(newPlace, this.person.getAccessiblePlaces());
    }

    /**
     * Method to remove actions when an actor leaves a place
     * 
     */
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

    /**
     * Method to repeat an action on all chunks of the land
     * @param action Action to repeat
     * @throws Exception 
     */
    protected void doAll(Action action) throws Exception {
        LandAbstract land = (LandAbstract) argument.getArg1();
        try {
            for (Chunk chunk : land.getElements()) {
                try {
                    this.enter(chunk);
                    this.argument.setArg1(chunk);
                    this.executeAction(action, this.argument);
                } catch (Exception e) {
                    throw new Exception(e.getCause());
                }
            }
        } finally {
            this.enter(land);
        }
    }
    

    /**
     * Method to damage a tool and remove it if it's worn out
     * @param tool Tool to damage
     * @return 
     * @throws NoItemFoundException
     * @throws NotEnoughItemsException
     */
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
