package Actors.Actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Actors.Person.Farmer;
import Actors.Person.Landlord;
import Actors.Person.Person;
import Exceptions.CustomExceptions.*;
import Item.ItemType;
import Item.Interface.Item;
import Main.Game;
import Place.Place;
import Place.Places;
import Place.Land.PlantChunk;
import Place.Land.PlantLand;
import Plants.PlantAbstract;

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
            }};;
    }

    public void executeAction(Action s,Object argument) {
        /*
         * Method to find a method to execute
         */

        // find the method and execute it
        try {
            if (this.availableActions.contains(s)){
            this.getClass().getMethod(s.name().toLowerCase()).invoke(null,argument);
            System.out.println(s + " executed.");
            } else throw new ActionNotAvailableException();  
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void enter(Place p) throws PlaceNotAvailableException {
        /*
         * Method to change actions when
         * an actors enters somewhere
         */
        // TODO indexing to get the place is not working properly
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
        person.getActions().updateActions(person.getPlace().getActions().getActions(), false);

        person.setPlace(null);
    }

    // METHODS FOR THE FARMER

    public void grab_item(){
        /*
         * Method to grab item from
         * the barn, remove its inventory
         * and add it the the farmer's inventory
         */
        Farmer f = (Farmer)this.person;
        // grab item from the barn, remove its inventory and add it the the farmer's inventory
        //Item item = f.getPlace().getInventory().get(itemIndex);
        //f.addItem(item);
        //App.getBarn().removeItem(itemIndex);
    }

    public void drop_item(int i){
        /*
         * Method to drop item on
         * the floor and lose it
         */
       ((Farmer)this.person).removeItem(i);
    }

    public void move_item(int i){
        /*
         * Method to move the item
         * to the barn from the farmer's inventory
         */
        Farmer farmer = (Farmer)this.person;
        Item item = farmer.getInventory().get(i);
        //farmer.getPlace().barn.getInventory().add(item);
        //farmer.removeItem(item);
    }
    
    public void plant(ArrayList<? extends Object> items) throws LandIsNotPlowedException, NoSeedFoundException{
        /*
         * Method to plant a plant
         */
        Farmer f = (Farmer)this.person;
        PlantChunk c = (PlantChunk)items.get(0);
        Item seed = (Item)items.get(1);
        //PlantChunk c = (PlantChunk)((PlantLand)f.getPlace()).getChunks().get(Game.GameData.secondIndex);

        
        // check if the farmer has a seed and the chunk is plowed
        if (seed instanceof PlantAbstract ){ 
            if(c.getDirtStatus()){
                // add plant to the land 
                c.setPlant((PlantAbstract)f.getItem(1));
                // add new possible actions
                c.getActions().updateActions(new HashSet<>(){{
                    add(Action.WATER);
                    add(Action.FERTILIZE);
                    }}, true);
            } else throw new LandIsNotPlowedException();
        } else throw new NoSeedFoundException();                                       
    }

    public void water(PlantChunk c) throws NoToolFoundException{
        /*
         * Method to water a plant
         */
        Farmer f = (Farmer)this.person;
        //PlantChunk c = (PlantChunk)((PlantLand)f.getPlace()).getChunks().get(Game.GameData.secondIndex);

        //check if the farmer has the watering can
        if ( this.damageItem(ItemType.Tools.WATERINGCAN)){
            // increase water level
            c.setWaterLevel(WATERING_INDEX);
        } else throw new NoToolFoundException();
    }

    public void plow(PlantChunk c) throws NoToolFoundException{
        /*
         * Method to plow dirt
         */
        Farmer f = (Farmer)this.person;
        //PlantChunk c = (PlantChunk)((PlantLand)f.getPlace()).getChunks().get(Game.GameData.secondIndex);

        //check if the farmer has the hoe
        if (this.damageItem(ItemType.Tools.HOE) && !c.getDirtStatus()){
            // change land status
            c.setDirtStatus(true);
            // add new possible actions
            c.getActions().updateActions(new HashSet<>(){{
                add(Action.PLANT);
                }}, true);
        }else throw new NoToolFoundException();
    }

    public void fertilize(PlantChunk c) throws NoToolFoundException{
        /*
         * Method to fertilize a plant
         */
        Farmer f = (Farmer)this.person;
        //PlantChunk c = (PlantChunk)((PlantLand)f.getPlace()).getChunks().get(Game.GameData.secondIndex);
        
        //check if the farmer has the fertilizer
        if (this.damageItem(ItemType.Tools.HOE)){ //TODO change with fertilizer
            // increase water level
            c.setFertilizationLevel(FERTILIZATION_INDEX);
        }else throw new NoToolFoundException();
    }

    public void harvest(PlantChunk c){
        /*
         * Method to harvest a plant
         */
        Farmer f = (Farmer)this.person;
        //PlantChunk c = (PlantChunk)((PlantLand)f.getPlace()).getChunks().get(Game.GameData.secondIndex);
        List<Item> resources = List.copyOf(c.getPlant().getProduct());
        int multiplier = 1;

        c.getPlant().turnToProduct();

        // if sickle is equipped double the harvested resources
        if (this.damageItem(ItemType.Tools.SICKLE)){
            multiplier = 2;
        }

        // TODO IF INVETORY IS NOT FULL 
        // add resources to the inventory
        for(Item item : resources){
            item.setNumber(item.getNumber()*multiplier);
            f.addItem(item); // TODO CHANGE TO ENABLE ITEM STACKING
        }
        
        // remove plant
        c.setPlant(null);
        c.resetActions();
    }

    public void water_all(PlantLand p){
        /*
         * Method to water all plants
         */
        this.doAll(Action.WATER,p);
    }

    public void fertilize_all(PlantLand p){
        /*
         * Method to fertilize all plants
         */
        this.doAll(Action.FERTILIZE,p);
    }

    public void harvest_all(PlantLand p){
        /*
         * Method to harvest all plants
         */
        this.doAll(Action.HARVEST,p);
    }

    public void plow_all(PlantLand p){
        /*
         * Method to plow dirt
         */
        this.doAll(Action.PLOW,p);
    }

    private void doAll(Action a, PlantLand p){
        /*
         * Method to repeat the same action on all chunks in a land
         */
        Farmer f = (Farmer)this.person;

        p.getChunks().forEach(chunk -> {f.getActions().executeAction(a,new ArrayList<>() {{add(chunk);}});});
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

    private boolean damageItem(ItemType.Tools t){
        /*
         * Use an item and destroy it if it's worn out
         */
        Farmer f = (Farmer)this.person;

        int tmp = f.searchItem(t);

        if (tmp != -1){
            f.getInventory().get(tmp).useItem();
            if (f.getInventory().get(tmp).getStatus() == 0){
                f.removeItem(tmp);
            }
            return true;
        }
        return false;
    }
}
