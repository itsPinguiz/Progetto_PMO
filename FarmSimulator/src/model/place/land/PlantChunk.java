package model.place.land;

import java.util.HashSet;
import java.util.Random;

import model.actors.actions.PlaceActions;
import model.actors.actions.ActionsManager.Action;
import model.item.plants.PlantAbstract;
import model.place.Places;


public class PlantChunk extends LandAbstract implements Chunk{
    /* Small part of a land that contains one plant */
    
    // attributes
    private PlantAbstract plant;
    private PlantLand land;
    private float waterLevel;
    private float fertilizationLevel;
    private boolean isPlowed;
    private Random random = new Random();

    // constructor
    PlantChunk(PlantAbstract plant, PlantLand land){
        super();
        super.type = Places.PLANT_CHUNK;
        this.plant = plant;
        this.land = land;
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
            this.setWaterLevel(-5);
            this.setFertilizationLevel(-5);
        }
        
        this.setWaterLevel(-1);
        this.setFertilizationLevel(-1);
    }

    public PlantLand getLand(){
        /*
         * Returns the land where the chunk is
         */
        return this.land;
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
        if ((this.waterLevel + value) < 0)
            this.waterLevel = 0;
        else if ((this.waterLevel + value) < 100){
            this.waterLevel =+ value;
        }else{
            this.waterLevel = 100;
        }
    }

    public void setFertilizationLevel(int value){
        /*
         * Increases fertilization value
         */

        if ((this.fertilizationLevel + value) < 0)
            this.fertilizationLevel = 0;
        else if ((this.fertilizationLevel + value) < 100)
            this.fertilizationLevel =+ value;
        else 
            this.fertilizationLevel = 100;
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
        return this.fertilizationLevel;
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
