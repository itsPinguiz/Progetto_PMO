package Actors.Actions;

import java.util.HashSet;

import Actors.Person.Farmer;
import Main.App;
import Place.Land.LandAbstract;
import Place.Land.PlantChunk;
import Place.Land.PlantLand;
import Plants.PlantAbstract;

public class FarmerActions extends PersonActions{
    private final int WATERING_INDEX = 10;
    private final int FERTILIZATION_INDEX = 10;
    
    public FarmerActions(Farmer farmer){
        super(farmer);
    }

    // METHODS FOR THE FARMER

    public void grabItem(){
        /*
         * Method to grab item from
         * the barn
         */
    }

    public void dropItem(){
        /*
         * Method to drop item on
         * the floor and lose it
         */
       ((Farmer)super.person).removeItem(App.itemIndex);
    }

    public void moveItem(){
        /*
         * Method to move the item
         * to the barn
         */
    }
    
    public void plant(){
        /*
         * Method to plant a plant
         */
        Farmer f = (Farmer)super.person;
        PlantLand p = (PlantLand)super.person.getPlace();

        // add plant to the land 
        if ( f.getInventory().get(App.itemIndex) instanceof PlantAbstract){
            p.getChunks().get(App.landIndex).setPlant((PlantAbstract)f.getInventory().get(App.itemIndex));
            // remove from inventory
            f.removeItem(App.itemIndex);
            // add new possible actions
            p.getActions().addAction(new HashSet<>(){{
                                        add("water");
                                        add("fertilize");
                                        }});
        }                                                         
        //TODO ONCE THE PLANT HAS GROWN, HARVEST WILL BE ADDED TO ACTIONS
    }

    public void water(){
        /*
         * Method to water a plant
         */
        Farmer f = (Farmer)super.person;
        PlantChunk c = (PlantChunk)((PlantLand)super.person.getPlace()).getChunks().get(App.landIndex);
        // increase water level
        c.setWaterLevel(WATERING_INDEX);
        // TODO IF THE FARMER HAS WATERING HOSE IN HIS INVENTORY
    }

    public void plow(){
        /*
         * Method to plow dirt
         */
        /*
        // change land status
        getChunk().setPlantable(True);
        // add new possible actions
        super.person.getPlace().addAction("plant");
        */
        // TODO IF THE FARMER HAS HOE IN HIS INVENTORY
    }

    public void fertilize(){
        /*
         * Method to fertilize a plant
         */
        /*
        // increase water level
        getChunk().setFertilization(getChunk().getFertilization() + FERTILIZATION_INDEX);
        */
        // TODO IF THE FARMER HAS FERTILIZER IN HIS INVENTORY
    }

    public void harvest(){
        /*
         * Method to harvest a plant
         */
        /*
        List<Item> resources = new ArrayList<>{};
        // add resources to the inventory
        for(Item item : resources){
            super.person.addItem(item)
        }
        // TODO HARVEST FASTER IF TOOL IS IN HIS INVENTORY
        // remove plant
        getChunk() = null;
        */

    }

    public void waterAll(){
        /*
         * Method to water all plants
         */
        /*
        App.itemIndex = 0;
        for (ChunkPlantLand chunk : super.person.getPlace().getElements()){
            super.person.getActions().executeAction(water);
            App.itemIndex++;
        }
         */
    }

    public void fertilizeAll(){
        /*
         * Method to fertilize all plants
         */
        /*
        App.itemIndex = 0;
        for (ChunkPlantLand chunk : super.person.getPlace().getElements()){
            super.person.getActions().executeAction(fertilize);
            App.itemIndex++;
        }
        */
    }

    public void harvestAll(){
        /*
         * Method to harvest all plants
         */
        /*
        App.itemIndex = 0;
        for (ChunkPlantLand chunk : super.person.getPlace().getElements()){
            super.person.getActions().executeAction(harvest);
            App.itemIndex++;
        }
        */
    }

    public void plowAll(){
        /*
         * Method to plow dirt
         */
        /*
        App.itemIndex = 0;
        for (ChunkPlantLand chunk : super.person.getPlace().getElements()){
            super.person.getActions().executeAction(plow);
            App.itemIndex++;
        }
        */
    }

    private Object getChunk(){
        //return super.person.getPlace().getElements()[App.itemIndex]
        return new Object();
    }

    
}
