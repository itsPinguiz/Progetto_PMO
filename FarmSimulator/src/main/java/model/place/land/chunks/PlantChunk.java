package model.place.land.chunks;

import java.util.HashSet;
import java.util.Random;

import model.Constants;
import model.actors.actions.PlaceActions;
import model.actors.actions.ActionsManager.Action;
import model.item.plants.PlantAbstract;
import model.item.plants.PlantAbstract.PlantLife;
import model.place.Places;
import model.place.land.PlantLand;


public class PlantChunk extends Chunk{
    /* Small part of a land that contains one plant */
    
    // attributes
    private PlantAbstract plant;
    private PlantLand land;
    private float waterLevel;
    private float fertilizationLevel;
    private boolean isPlowed;
    private Random random = new Random();

    // constructor
    public PlantChunk(PlantAbstract plant, PlantLand land){
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
            this.increaseWaterLevel(Constants.PLANT_WATER_DEACRESE);
            this.increaseFertilizationLevel(Constants.PLANT_FERTILIZATION_DEACRESE);

            if (this.plant.getLifeStage() == PlantLife.DEAD){
                this.plant = null;
                this.resetActions();
            } else if (this.waterLevel <= 100){
                this.getActions().updateActions(new HashSet<>(){{
                    add(Action.WATER);
                }},false);
            } else if (this.fertilizationLevel <= 100){
                this.getActions().updateActions(new HashSet<>(){{
                    add(Action.FERTILIZE);
                }},false);
            } else {
                this.getActions().updateActions(new HashSet<>(){{
                    add(Action.WATER);
                    add(Action.FERTILIZE);
                }},true);
            }
        }
        
        this.increaseWaterLevel(Constants.CHUNK_WATER_DEACRESE);
        this.increaseFertilizationLevel(Constants.CHUNK_FERTILIZATION_DEACRESE);
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

    public void increaseWaterLevel(int value){
        /*
         * Increases water value
         */
        if ((this.waterLevel + value) <= 0)
            this.waterLevel = 0;
        else if ((this.waterLevel + value) < Constants.WATERING_MAX){
            this.waterLevel += value;
        }else{
            this.waterLevel = Constants.WATERING_MAX;
        }
    }

    public void increaseFertilizationLevel(int value){
        /*
         * Increases fertilization value
         */

        if ((this.fertilizationLevel + value) < 0)
            this.fertilizationLevel = 0;
        else if ((this.fertilizationLevel + value) < Constants.FERTILIZATION_MAX)
            this.fertilizationLevel += value;
        else 
            this.fertilizationLevel = Constants.FERTILIZATION_MAX;
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

    public boolean isPlowed(){
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

    @Override
    public void resetActions(){
        /*
         * Resets plantChunk's actions
         */
        this.getActions().resetActions();
        this.getActions().updateActions( new HashSet<>(){{
                                            add(isPlowed()?Action.PLANT:Action.PLOW);
                                            }}, true);
    }

}
