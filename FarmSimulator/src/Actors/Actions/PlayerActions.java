package Actors.Actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;

import Actors.Person.Farmer;
import Actors.Person.Landlord;
import Actors.Person.Person;
import Exceptions.CustomExceptions.*;
import Item.ItemType;
import Item.Animal.AnimalAbstract;
import Item.Interface.Item;
import Item.Plants.PlantAbstract;
import Place.Place;
import Place.Places;
import Place.Land.AnimalLand;
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
            add(Places.MARKET);
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
            // the landlord cannot move items
            if (!(person instanceof Landlord && p.getType() == Places.BARN)){
                this.person.getActions().updateActions(person.getPlace().getActions().getActions(), true);
            }
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

    public void move_item(ArrayList<? extends Object> items) throws CloneNotSupportedException{
        /*
         * Method to move the item
         * to the barn from the farmer's inventory
         */
        Item itemToMove = (Item)items.get(1);
        Farmer farmer = (Farmer)this.person;
        Barn barn = (Barn)farmer.getPlace();

        try {
            if (barn.getBarnInventory().getInventory().contains(itemToMove)){
                // if the item is in the barn, remove it and add it to the farmer's inventory
                try {
                    farmer.getInventory().addItem(itemToMove);
                    barn.getBarnInventory().removeItem(itemToMove);
                } catch (InventoryIsFullException e) {
                    // if inventory is full, add the item back to the barn
                    barn.getBarnInventory().addItem(itemToMove);
                }
            } else if (farmer.getInventory().getInventory().contains(itemToMove)){
                // if the item is in the inventory, remove it and add it to the barn
                try {
                    barn.getBarnInventory().addItem(itemToMove);
                    farmer.getInventory().removeItem(itemToMove);
                } catch (InventoryIsFullException e) {
                    // if inventory is full, add the item back to the farmer's inventory
                    farmer.getInventory().addItem(itemToMove);
                }
            }
        } catch (NoItemFoundException | InventoryIsFullException e) {
            e.printStackTrace();
        }
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
        if (tool.getType() == ItemType.Tools.WATERINGCAN && tool.getStatus() > 0&& this.damageTool(tool)){
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
        if (tool.getType() == ItemType.Tools.HOE && tool.getStatus() > 0 && this.damageTool(tool)){
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
        if (tool.getType() == ItemType.Tools.FERTILIZER && tool.getStatus() > 0&& this.damageTool(tool)){ 
            // increase water level
            c.setFertilizationLevel(FERTILIZATION_INDEX);
        }else throw new NoToolFoundException(tool.getType(),ItemType.Tools.FERTILIZER);
    }

    public void harvest(ArrayList<? extends Object> items) throws InventoryIsFullException{
        /*
         * Method to harvest a plant
         */
        PlantChunk c = (PlantChunk)items.get(0);
        Item tool = (Item)items.get(1);
        
        Farmer f = (Farmer)this.person;

        Item product = c.getPlant().getProduct();
        int multiplier = 1;

        // if sickle is equipped double the harvested resources
        if (tool.getType() == ItemType.Tools.SICKLE && tool.getStatus() > 0&& this.damageTool(tool)){
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
        this.doAll(items,Action.WATER);
    }

    public void fertilize_all(ArrayList<? extends Object> items)throws NoSuchMethodException{
        /*
         * Method to fertilize all plants
         */
        this.doAll(items,Action.FERTILIZE);
    }

    public void harvest_all(ArrayList<? extends Object> items) throws NoSuchMethodException{
        /*
         * Method to harvest all plants
         */
        this.doAll(items,Action.HARVEST);
    }

    public void plow_all(ArrayList<? extends Object> items) throws NoSuchMethodException{
        /*
         * Method to plow dirt
         */
        this.doAll(items,Action.PLOW);
    }

    private void doAll(ArrayList<? extends Object> items, Action action){
        /*
         * Method to repeat the same action on all chunks in a land
         */

        if (items.get(0) instanceof PlantLand){
            PlantLand p = (PlantLand)items.get(0);
            p.getChunks().forEach(chunk -> 
            {try {
                if (chunk.getActions().getActions().contains(action)){
                    this.getMethodByName((action.toString().toLowerCase()).replace(' ', '_')).invoke(this,new ArrayList<>() {{add(chunk); add(items.get(1));}});
                } else {
                    throw new ActionNotAvailableException(action,chunk.getActions().getActions());
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException|ActionNotAvailableException e) {
                System.out.println(e); //TODO: might change to printstacktace
            }});
        } else if (items.get(0) instanceof AnimalLand){
            AnimalLand a = (AnimalLand)items.get(0);
            a.getElements().forEach(animal -> 
            {try {
                if (a.getActions().getActions().contains(action)){
                    System.out.println((action.toString().toLowerCase()).replace(' ', '_'));
                    this.getMethodByName((action.toString().toLowerCase()).replace(' ', '_')).invoke(this,new ArrayList<>() {{add(a); add(animal);}});
                } else {
                    throw new ActionNotAvailableException(action,a.getActions().getActions());
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException|ActionNotAvailableException e) {
                System.out.println(e); //TODO: might change to printstacktace
            }});
        }
    }

    public void add_animal(ArrayList<? extends Object> items) throws NoItemFoundException, InventoryIsFullException{
        /*
         * Method to add an animal to the farm
         */
        AnimalAbstract animal = (AnimalAbstract)items.get(1);
        Farmer farmer = (Farmer)this.person;

        try {
            farmer.getInventory().removeItem(animal);
            ((AnimalLand)(farmer.getPlace())).addAnimal(animal);
            ((AnimalLand)(farmer.getPlace())).getActions().updateActions(new HashSet<Action>(){{add(Action.GET_RESOURCES);add(Action.GET_ALL_RESOURCES);}}, true);
        } catch (InventoryIsFullException e) {
            farmer.getInventory().addItem(animal);
        }
    }

    public void remove_animal(ArrayList<? extends Object> items) throws NoItemFoundException, InventoryIsFullException{
        /*
         * Method to remove an animal from the farm
         */
        AnimalAbstract animal = (AnimalAbstract)items.get(1);
        Farmer farmer = (Farmer)this.person;

        try {
            ((AnimalLand)(farmer.getPlace())).removeAnimal(animal);
            farmer.getInventory().addItem(animal);
        } catch (InventoryIsFullException e) {
            ((AnimalLand)(farmer.getPlace())).addAnimal(animal);
        }
    }

    public void get_resources(ArrayList<? extends Object> items) throws InventoryIsFullException{
        /*
         * Method to get resources from the farm
         */
        AnimalAbstract animal = (AnimalAbstract)items.get(1);
        Farmer farmer = (Farmer)this.person;

        animal.getProducts().forEach(item -> {
            try {
                farmer.getInventory().addItem(item);
            } catch (InventoryIsFullException | NoItemFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void get_all_resources(ArrayList<? extends Object> items) throws InventoryIsFullException{
        /*
         * Method to get all resources from the farm
         */

        doAll(items, Action.GET_RESOURCES);
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

    // METHODS FOR THE LANDLORD

    public void buy_item(ArrayList<? extends Object> items) throws NoItemFoundException, InventoryIsFullException, CloneNotSupportedException{
        /*
         * Method to buy item
         * from the market
         */
        Item item = (Item)items.get(1);
        Landlord landlord = (Landlord)this.person;
        Barn barn = (Barn)landlord.getPlace();
        Item boughtItem = null;

        try {
            // buy from maket 
            boughtItem = barn.getMarket().buyItem(item, 1);
             // add it to the barn
            barn.getBarnInventory().addItem(boughtItem);
            // remove the money from the balance
            landlord.setBalance(landlord.getBalance() - boughtItem.getPrice());
        } catch (NoEnoughMoneyException e) {
            e.printStackTrace();
        } catch (InventoryIsFullException e){
            // give money back and add the item back to the market
            if (boughtItem != null){
                landlord.setBalance(landlord.getBalance() + boughtItem.getPrice());
                barn.getMarket().getItemShop().addItem(boughtItem);
            }
        }
        
        // TODO: if the bought item is a land add it to the lands
    }

    public void sell_item(ArrayList<? extends Object> items){
        /*
         * Method to sell item
         * to the market
         */
        Item item = (Item)items.get(1);
        Landlord landlord = (Landlord)this.person;
        Barn barn = (Barn)landlord.getPlace();

        try {
            if (barn.getMarket().getItemShop().getInventory().contains(item)){
                // remove item from the barn
                barn.getBarnInventory().removeItem(item);
                // add money to the balance
                landlord.setBalance(landlord.getBalance() + item.getPrice());
            }
        } catch (NoItemFoundException e) {
            e.printStackTrace();
        }
        }
    

    
}
