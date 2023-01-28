package Place.Land;

import Actors.Actions.PlaceActions;
import Calendar.Calendar.Weather;
import Plants.PlantAbstract;

public class PlantChunk extends LandAbstract implements Chunk{
    /* Small part of a land that contains one plant */
    
    // attributes
    private PlantAbstract plant;
    private float waterLevel;
    private float fertilizationLevel;
    private boolean isPlowed;

    // constructor
    PlantChunk(PlantAbstract plant){
        this.plant = plant;
        this.actions = new PlaceActions(this);
    }

    //methods
    public PlantAbstract getPlant(){
        /*
         * Returns the planted plant
         */
        return this.plant;
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

    public boolean getDirtStatus(){
        /*
         * Returns if chunk is plowed or not
         */
        return this.isPlowed;
    }

    public void setDirtStatus(boolean b){
        /*
         * Returns if chunk is plowed or not
         */
        this.isPlowed = b;
    }

    public int getNumElements(){
        /*
         * Returns whether there is a plant or not
         */
        return (this.plant == null)?0:1;
    }

    public void growPlant(){
        /*
         * Uses a formula to grow the plant
         */
        this.plant.getLife().valueOf(this.plant.getLife().doubleValue() - // previous life value
                                     2*this.plant.getWaterNeed()/this.waterLevel + // waterneed devided by water level
                                     this.fertilizationLevel/this.calendar.getWeather().ordinal()); // fertilization level devided by weather
    }

}
