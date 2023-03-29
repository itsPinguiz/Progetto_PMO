package Place.Land;

import java.util.HashSet;
import java.util.Random;

import Actors.Actions.PlaceActions;
import Actors.Actions.ActionsManager.Action;
import Place.Places;
import Plants.PlantAbstract;


public class PlantChunk extends LandAbstract implements Chunk{
    /* Small part of a land that contains one plant */
    
    // attributes
    private PlantAbstract plant;
    private float waterLevel;
    private float fertilizationLevel;
    private boolean isPlowed;
    private Random random = new Random();

    // constructor
    PlantChunk(PlantAbstract plant){
        super();
        super.type = Places.PLANT_CHUNK;
        this.plant = plant;
        this.actions = new PlaceActions(this);
        this.waterLevel = random.nextInt(10) + 1;
        this.fertilizationLevel = random.nextInt(10) + 1;
    }

    //methods
    @Override
    public void update(){
        /*
         *  Grows the plant and updates the chunk's status
         */
        if (this.plant != null){
            this.plant.grow();
            this.waterLevel -= 5;
            this.fertilizationLevel -= 5;
        }
        
        this.waterLevel -= 1;
        this.fertilizationLevel -= 1;
    }

    public void setPlant(PlantAbstract plant){
        /*
         * sets the plant
         */
        this.plant = plant;
    }

    public void setWaterLevel(int value){
        /*
         * Increases water value
         */
        if ((this.waterLevel + value) < 100){
            this.waterLevel =+ value;
        }else{
            this.waterLevel = 100;
        }
    }

    public void setFertilizationLevel(int value){
        /*
         * Increases fertilization value
         */
        if ((this.fertilizationLevel + value) < 100){
            this.fertilizationLevel =+ value;
        }else{
            this.fertilizationLevel = 100;
        }
    }

    public void setDirtStatus(boolean b){
        /*
         * Returns if chunk is plowed or not
         */
        this.isPlowed = b;
    }

    public float getWaterLevel(){
        /*
         * Returns the chunk's water level
         */
        return this.waterLevel;
    }

    public float getFertilizationLevel(){
        /*
         * Returns the chunk's fertilization level
         */
        return this.waterLevel;
    }

    public boolean getDirtStatus(){
        /*
         * Returns if chunk is plowed or not
         */
        return this.isPlowed;
    }

    public PlantAbstract getPlant(){
        /*
         * Returns the planted plant
         */
        return this.plant;
    }

    public void resetActions(){
        /*
         * Resets plantChunk's actions
         */
        this.getActions().resetActions();
        this.getActions().updateActions( new HashSet<>(){{
                                            add(Action.PLOW);
                                            }}, true);
    }

}
