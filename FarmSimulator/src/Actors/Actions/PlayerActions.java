package Actors.Actions;

import java.util.HashSet;
import java.util.List;

import Actors.Person.Farmer;
import Actors.Person.Landlord;
import Actors.Person.Person;
import Item.Interface.Item;
import Main.Game;
import Place.Places;
import Place.Land.PlantChunk;
import Place.Land.PlantLand;
import Plants.PlantAbstract;

public class PlayerActions extends ActionsManager{
    private final int WATERING_INDEX = 10;
    private final int FERTILIZATION_INDEX = 10;
    private Person person;
    private HashSet<Places> accessiblePlaces;
    
    public PlayerActions(Landlord p){
        this.person = p;
        this.accessiblePlaces = new HashSet<>(){{
            add(Places.BARN);
            }};;
    }

    public PlayerActions(Farmer p){
        this.person = p;
        this.accessiblePlaces = new HashSet<>(){{
            add(Places.BARN);
            add(Places.ANIMAL_LAND);
            add(Places.PLANT_LAND);
            }};;
    }

    public void executeAction(Action s) {
        /*
         * Method to find a method to execute
         */

        // find the method and execute it
        if (this.availableActions.contains(s)){
            try {
            this.getClass().getMethod(s.name()).invoke(null);
            System.out.println(s + " executed.");
            } catch (Exception e) {
                System.out.println("Wasn't able to execute action due to " + e);
            } 
        } else {
            System.out.println("Action is not available.");
        }
    }

    public void enter() {
        /*
         * Method to change actions when
         * an actors enters somewhere
         */
        if (this.accessiblePlaces.contains(Game.GameData.map.get(Game.GameData.firstIndex).get(1).getType())){
            if (person.getPlace() != null)
                leave();
            this.person.getActions().updateActions(person.getPlace().getActions().getActions(), true);
        } else{
            System.out.printf("The %s cannot enter the lands\\", (this.person instanceof Landlord)?"Landlord":"Farmer");
        }
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

    public void drop_item(){
        /*
         * Method to drop item on
         * the floor and lose it
         */
       ((Farmer)this.person).removeItem(Game.GameData.firstIndex);
    }

    public void move_item(){
        /*
         * Method to move the item
         * to the barn from the farmer's inventory
         */
        Farmer farmer = (Farmer)this.person;
        Item item = farmer.getInventory().get(Game.GameData.firstIndex);
        //farmer.getPlace().barn.getInventory().add(item);
        //farmer.removeItem(item);
    }
    
    public void plant(){
        /*
         * Method to plant a plant
         */
        Farmer f = (Farmer)this.person;
        PlantLand p = (PlantLand)this.person.getPlace();

        // add plant to the land 
        if ( f.getInventory().get(Game.GameData.firstIndex) instanceof PlantAbstract){
            p.getChunks().get(Game.GameData.secondIndex).setPlant((PlantAbstract)f.getInventory().get(Game.GameData.firstIndex));
            // remove from inventory
            f.removeItem(Game.GameData.firstIndex);
            // add new possible actions
            p.getActions().updateActions(new HashSet<>(){{
                add(Action.WATER);
                add(Action.FERTILIZE);
                }}, true);
        }                                                         
        //TODO ONCE THE PLANT HAS GROWN, HARVEST WILL BE ADDED TO ACTIONS
    }

    public void water(){
        /*
         * Method to water a plant
         */
        Farmer f = (Farmer)this.person;
        PlantChunk c = (PlantChunk)((PlantLand)f.getPlace()).getChunks().get(Game.GameData.secondIndex);
        // increase water level
        c.setWaterLevel(WATERING_INDEX);
        // TODO IF THE FARMER HAS WATERING HOSE IN HIS INVENTORY
    }

    public void plow(){
        /*
         * Method to plow dirt
         */
        Farmer f = (Farmer)this.person;
        PlantChunk c = (PlantChunk)((PlantLand)f.getPlace()).getChunks().get(Game.GameData.secondIndex);

        // change land status
        c.setDirtStatus(true);
        // add new possible actions
        c.getActions().updateActions(new HashSet<>(){{
            add(Action.PLANT);
            }}, true);

            
        // TODO IF THE FARMER HAS HOE IN HIS INVENTORY
    }

    public void fertilize(){
        /*
         * Method to fertilize a plant
         */

        Farmer f = (Farmer)this.person;
        PlantChunk c = (PlantChunk)((PlantLand)f.getPlace()).getChunks().get(Game.GameData.secondIndex);
        // increase water level
        c.setFertilizationLevel(FERTILIZATION_INDEX);
        // TODO IF THE FARMER HAS FERTILIZER IN HIS INVENTORY
    }

    public void harvest(){
        /*
         * Method to harvest a plant
         */

        Farmer f = (Farmer)this.person;
        PlantChunk c = (PlantChunk)((PlantLand)f.getPlace()).getChunks().get(Game.GameData.secondIndex);

        List<Item> resources = List.copyOf(c.getPlant().getProduct());
        c.getPlant().turnToProduct();

        // add resources to the inventory
        for(Item item : resources){
            f.addItem(item);
        }
        // TODO MODIFIER IF TOOL IS EQUIPPED
        
        // remove plant
        ((PlantChunk)((PlantLand)f.getPlace()).getChunks().get(Game.GameData.secondIndex)).setPlant(null);
    }

    public void water_all(){
        /*
         * Method to water all plants
         */
        /*
        Game.GameData.firstIndex = 0;
        for (ChunkPlantLand chunk : this.person.getPlace().getElements()){
            this.person.getActions().executeAction(water);
            Game.GameData.firstIndex++;
        }
         */
    }

    public void fertilize_all(){
        /*
         * Method to fertilize all plants
         */
        /*
        Game.GameData.firstIndex = 0;
        for (ChunkPlantLand chunk : this.person.getPlace().getElements()){
            this.person.getActions().executeAction(fertilize);
            Game.GameData.firstIndex++;
        }
        */
    }

    public void harvest_all(){
        /*
         * Method to harvest all plants
         */
        /*
        Game.GameData.firstIndex = 0;
        for (ChunkPlantLand chunk : this.person.getPlace().getElements()){
            this.person.getActions().executeAction(harvest);
            Game.GameData.firstIndex++;
        }
        */
    }

    public void plow_all(){
        /*
         * Method to plow dirt
         */
        /*
        Game.GameData.firstIndex = 0;
        for (ChunkPlantLand chunk : this.person.getPlace().getElements()){
            this.person.getActions().executeAction(plow);
            Game.GameData.firstIndex++;
        }
        */
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
}
