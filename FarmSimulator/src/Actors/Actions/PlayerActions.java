package Actors.Actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.tools.Tool;

import Actors.Person.Farmer;
import Actors.Person.Landlord;
import Actors.Person.Person;
import Exceptions.CustomExceptions.*;
import Inventory.Inventory;
import Item.ItemType;
import Item.Interface.Item;
import Item.Plants.PlantAbstract;
import Place.Place;
import Place.Places;
import Place.Land.LandAbstract;
import Place.Land.PlantChunk;
import Place.Land.PlantLand;
import Place.Barn.Barn;
import Item.Tools.Interface.AbstractTool;

public class PlayerActions extends ActionsManager{
    // attributes
    private final int WATERING_INDEX = 10;
    private final int FERTILIZATION_INDEX = 10;
    private Person person;
    private HashSet<Places> accessiblePlaces;

    // constructor for Landlord    
    public PlayerActions(Landlord p){
        this.person = p;
        this.accessiblePlaces = new HashSet<>(){{
            add(Places.BARN);
            }};;
    }
    
    // constructor for Farmer
    public PlayerActions(Farmer p){
        this.person = p;
        this.accessiblePlaces = new HashSet<>(){{
            add(Places.BARN);
            add(Places.ANIMAL_LAND);
            add(Places.PLANT_LAND);
            add(Places.PLANT_CHUNK);
            }};;
    }

    public void executeAction(Action s,Object argument) throws IllegalAccessException,
                                                               IllegalArgumentException,
                                                               InvocationTargetException,
                                                               SecurityException,
                                                               ActionNotAvailableException {
        /*
        * Method to find a method to execute
        */

        // find the desired method
        Method method = getMethodByName(s.name());

        // execute the method if it exists and is available
        if (method != null && this.availableActions.contains(s)) {
            person.getActions().updateActions(person.getPlace().getActions().getActions(), false);
            method.invoke(this,argument);        
            person.getActions().updateActions(person.getPlace().getActions().getActions(), true);
        } else {
            throw new ActionNotAvailableException(s, this.availableActions);
        }
    }

    private  Method getMethodByName(String methodName) {
        /*
         * Method to get a method by its name
         */
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method m : methods) {
            if (m.getName().equals(methodName.toLowerCase())) {
                return m;
            }
        }
        return null;
    }

    public int getActionReqArgs(Action s) throws ActionNotAvailableException{
        /*
         * Method to get the number of arguments
         * required for the action
         */
        int numParams = 0;

        if (this.availableActions.contains(s)){
            Method[] methods = PlayerActions.class.getDeclaredMethods();

            // cerca il metodo desiderato
            Method method = null;
            for (Method m : methods) {
                if (m.getName().equals(s.name().toLowerCase())) {
                    method = m;
                    break;
                }
            }
            if (method != null){
                numParams = method.getParameterCount();
            }
        }
        return numParams;
    }

    public void enter(Place p) throws PlaceNotAvailableException {
        /*
         * Method to change actions when
         * an actors enters somewhere
         */
        if (this.accessiblePlaces.contains(p.getType())){
            if (person.getPlace() != null)
                leave();
            this.person.setPlace(p);
            this.person.getActions().updateActions(person.getPlace().getActions().getActions(), true);
        } else throw new PlaceNotAvailableException();
    }

    public void leave() {
        /*
         * Method to change actions when
         * an actors leaves from somewhere
         */
        if (person.getPlace()!= null){
            person.getActions().updateActions(person.getPlace().getActions().getActions(), false);
        }
        person.setPlace(null);
    }

    // METHODS FOR THE FARMER

    public void grab_item(Item item){
        /*
         * Method to grab item from
         * the barn, remove its inventory
         * and add it the the farmer's inventory
         */
        Farmer f = (Farmer)this.person;
        // grab item from the barn, remove its inventory and add it the the farmer's inventory
        Inventory barnInventory = ((Barn)(f.getPlace())).getBarnInventory();
        try {
            if (barnInventory.removeItem(item) != -1){
                f.getInventory().addItem(item);
            }
        } catch (NoItemFoundException e) {
            e.printStackTrace();
        }
        
        //App.getBarn().removeItem(itemIndex);
    }

    public void drop_item(Item item){
        /*
         * Method to drop item on
         * the floor and lose it
         */
       try {
        ((Farmer)this.person).getInventory().removeItem(item);
    } catch (NoItemFoundException e) {
        e.printStackTrace();
    }
    }

    public void move_item(int nItem,Item item) throws CloneNotSupportedException{
        /*
         * Method to move the item
         * to the barn from the farmer's inventory
         */
        Farmer farmer = (Farmer)this.person;
        try {
            Item itemToMove = farmer.getInventory().getItem(nItem,item);
        } catch (NoItemFoundException e) {
            e.printStackTrace();
        }
        //farmer.getPlace().barn.getInventory().add(item);
        //farmer.removeItem(item);
    }
    
    public void plant(ArrayList<? extends Object> items) throws LandIsNotPlowedException, NoSeedFoundException, CloneNotSupportedException{
        /*
         * Method to plant a plant
         */
        Farmer f = (Farmer)this.person;
        PlantChunk c = (PlantChunk)items.get(0);
        Item seed = (Item)items.get(1);

        // check if the farmer has a seed and the chunk is plowed
        if (seed instanceof PlantAbstract ){ 
            if(c.getDirtStatus()){
                // add plant to the land 
                try {
                    c.setPlant((PlantAbstract)(f.getInventory().getItem(1,seed)));
                    c.getPlant().planted(c);
                } catch (NoItemFoundException e) {
                    e.printStackTrace();
                }
                // add new possible actions
                c.getActions().updateActions(new HashSet<>(){{
                    add(Action.WATER);
                    add(Action.FERTILIZE);
                    }}, true);
                c.getActions().updateActions(new HashSet<>(){{
                    add(Action.PLANT);
                    }}, false);
            } else throw new LandIsNotPlowedException();
        } else throw new NoSeedFoundException();                                       
    }

    public void water(ArrayList<? extends Object> items) throws NoToolFoundException{
        /*
         * Method to water a plant
         */
        PlantChunk c = (PlantChunk)items.get(0);
        Item tool = (Item)items.get(1);

        //check if the farmer has the watering can
        if (tool.getType() == ItemType.Tools.WATERINGCAN && this.damageTool(tool)){
            // increase water level
            c.setWaterLevel(WATERING_INDEX);
        } else throw new NoToolFoundException(tool.getType(),ItemType.Tools.WATERINGCAN);
    }

    public void plow(ArrayList<? extends Object> items) throws NoToolFoundException, LandIsAlreadyPlowedException{
        /*
         * Method to plow dirt
         */
        PlantChunk c = (PlantChunk)items.get(0);
        Item tool = (Item)items.get(1);
        
        //check if the farmer has the hoe
        if (tool.getType() == ItemType.Tools.HOE && this.damageTool(tool)){
            if (!c.getDirtStatus()){
            // change land status
            c.setDirtStatus(true);
            // add new possible actions
            c.getActions().updateActions(new HashSet<>(){{
                add(Action.PLANT);
                }}, true);
            c.getActions().updateActions(new HashSet<>(){{
                add(Action.PLOW);
                }}, false);
            } else throw new LandIsAlreadyPlowedException();
        }else throw new NoToolFoundException(tool.getType(),ItemType.Tools.HOE);
    }

    public void fertilize(ArrayList<? extends Object> items) throws NoToolFoundException{
        /*
         * Method to fertilize a plant
         */
        PlantChunk c = (PlantChunk)items.get(0);
        Item tool = (Item)items.get(1);

        //check if the farmer has the fertilizer
        if (tool.getType() == ItemType.Tools.FERTILIZER && this.damageTool(tool)){ 
            // increase water level
            c.setFertilizationLevel(FERTILIZATION_INDEX);
        }else throw new NoToolFoundException(tool.getType(),ItemType.Tools.FERTILIZER);
    }

    public void harvest(ArrayList<? extends Object> items){
        /*
         * Method to harvest a plant
         */
        PlantChunk c = (PlantChunk)items.get(0);
        Item tool = (Item)items.get(1);
        
        Farmer f = (Farmer)this.person;

        Item product = c.getPlant().getProduct();
        int multiplier = 1;

        // if sickle is equipped double the harvested resources
        if (tool.getType() == ItemType.Tools.SICKLE && this.damageTool(tool)){
            multiplier = 2;
        }

        // add resources to the inventory

        product.setNumber(product.getNumber()*multiplier);
        try {
            f.getInventory().addItem(product);
        } catch (NoItemFoundException e) {
            e.printStackTrace();
        } 
        
        // remove plant
        c.setPlant(null);
        c.resetActions();
    }

    public void water_all(ArrayList<? extends Object> items) throws NoSuchMethodException{
        /*
         * Method to water all plants
         */
        this.doAll(items,this.getMethodByName("water"));
    }

    public void fertilize_all(ArrayList<? extends Object> items)throws NoSuchMethodException{
        /*
         * Method to fertilize all plants
         */
        this.doAll(items,this.getMethodByName("fertilize"));
    }

    public void harvest_all(ArrayList<? extends Object> items) throws NoSuchMethodException{
        /*
         * Method to harvest all plants
         */
        this.doAll(items,this.getMethodByName("harvest"));
    }

    public void plow_all(ArrayList<? extends Object> items) throws NoSuchMethodException{
        /*
         * Method to plow dirt
         */
        this.doAll(items,this.getMethodByName("plow"));
    }

    private void doAll(ArrayList<? extends Object> items, Method method){
        /*
         * Method to repeat the same action on all chunks in a land
         */
        PlantLand p = (PlantLand)items.get(0);

        p.getChunks().forEach(chunk -> {try {
            method.invoke(this,new ArrayList<>() {{add(chunk); add(items.get(1));}});
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
            e.printStackTrace();
        }});
    }

    public void buy(){
        /*
         * Method to buy item
         * from the market
         */
        // remove from maket 
        // add it to the barn 
        // if the bought item is a land add it to the lands
        // remove the money from the balance
    }

    public void sell(){
        /*
         * Method to sell item
         * to the market
         */
        // remove from barn 
        // add the money in the balance
    }

    private boolean damageTool(Item tool){
        /*
         * Use an item and destroy it if it's worn out
         */
        Farmer f = (Farmer)this.person;

        try{
            if (tool != null){
                ((AbstractTool)tool).useTool();
                if (tool.getStatus() == 0){
                    f.getInventory().removeItem(tool);
                }
                return true;
            }
        } catch (NoItemFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
