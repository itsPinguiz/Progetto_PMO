package model.place.land.chunks;

import java.util.HashSet;
import java.util.Random;

import model.Constants;
import model.actors.actions.ActionsManager.Action;
import model.actors.actions.placeActions.PlaceActions;
import model.item.plants.Plant;
import model.item.plants.Plant.PlantLife;
import model.place.Places;
import model.place.land.landTypes.PlantLand;

/**
 * Piece of land that contains a plant
 */
public class PlantChunk extends Chunk{    
    /**
     * Attributes
     */
    private Plant plant;
    private PlantLand land;
    private float waterLevel;
    private float fertilizationLevel;
    private boolean isPlowed;
    private Random random = new Random(); //random number generator

    /**
     * Constructor
     * @param plant
     * @param land
     */
    public PlantChunk(Plant plant, PlantLand land){
        super();
        super.type = Places.PLANT_CHUNK;
        this.plant = plant;
        this.land = land;
        this.actions = new PlaceActions(this);
        this.waterLevel = random.nextInt(10) + 1;
        this.fertilizationLevel = random.nextInt(10) + 1;
    }

    /**
     *  Grows the plant and updates the chunk's status
     * 
     */
    @Override
    public void update(){
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

    /** 
     * Returns the land where the chunk is
     * @return 
     */
    public PlantLand getLand(){
        return this.land;
    }

    /**
     * Sets the plant
     * @param plant
     * 
     */
    public void setPlant(Plant plant){
        this.plant = plant;
    }

    /**
     * Increases water value
     * @param value
     * 
     */
    public void increaseWaterLevel(int value){
        if ((this.waterLevel + value) <= 0)
            this.waterLevel = 0;
        else if ((this.waterLevel + value) < Constants.WATERING_MAX){
            this.waterLevel += value;
        }else{
            this.waterLevel = Constants.WATERING_MAX;
        }
    }

    /**
     * Increases fertilization value
     * @param value
     * 
     */
    public void increaseFertilizationLevel(int value){
        if ((this.fertilizationLevel + value) < 0)
            this.fertilizationLevel = 0;
        else if ((this.fertilizationLevel + value) < Constants.FERTILIZATION_MAX)
            this.fertilizationLevel += value;
        else 
            this.fertilizationLevel = Constants.FERTILIZATION_MAX;
    }

    /**
     * Sets the status to plowed or not
     * @param newStatus
     * 
     */
    public void setDirtStatus(boolean newStatus){
        this.isPlowed = newStatus;
    }

    /**
     * Returns in the chunk is plowed or not
     * @return 
     */
    public boolean getDirtStatus(){
        return this.isPlowed;
    }

    /**
     * Returns the chunk's water level
     * @return 
     */
    public float getWaterLevel(){
        return this.waterLevel;
    }

    /**
     * Returns the chunk's fertilization level
     * @return 
     */
    public float getFertilizationLevel(){
        return this.fertilizationLevel;
    }

    /**
     * Returns if chunk is plowed or not
     * @return 
     */
    public boolean isPlowed(){
        return this.isPlowed;
    }

    /**
     * Returns the planted plant
     * @return 
     */
    public Plant getPlant(){
        return this.plant;
    }

    /**
     * Resets plantChunk's actions
     * 
     */
    @Override
    public void resetActions(){
        this.getActions().resetActions();
        this.getActions().updateActions( new HashSet<>(){{
                                            add(isPlowed()?Action.PLANT:Action.PLOW);
                                            }}, true);
    }

}
