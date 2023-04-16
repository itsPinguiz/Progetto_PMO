package model.place.land;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.actors.actions.PlaceActions;
import model.actors.actions.ActionsManager.Action;
import model.item.plants.PlantAbstract.PlantLife;
import model.place.Places;
import model.place.land.chunks.PlantChunk;

public class PlantLand extends LandAbstract{
    /* Land that contains chunks of land with plants */

    // attributes
    ArrayList<PlantChunk> chunks;
    
    // constructor
    public PlantLand(){
        super.type = Places.PLANT_LAND;
        this.chunks = new ArrayList<>(
            IntStream.range(0, 10)
                    .mapToObj(i -> new PlantChunk(null,this))
                    .collect(Collectors.toList())
        );
        this.actions = new PlaceActions(this);
    }

    // methods
    @Override
    public int getNumElements() {
        /*
         * Returns the number of land present
         * in the land
         */
        int elements = 0;

        for (PlantChunk p: this.chunks){
            if (p.getPlant() != null){
                elements += 1;
            }
        }
        return elements;
    }

    public void update(){
        /*
         * Updates all the chunks in the land
         */
        int count = 0;
        for (PlantChunk chunk: this.chunks){
            if (chunk.getPlant() == null){
                break;
            }
            chunk.update();

            // check if there are harvestable plants
            if (chunk.getPlant().getLifeStage()==PlantLife.HARVESTABLE){
                count++;
            }  
        }

        if (count == 0 || this.getNumElements() == 0){
            this.getActions().updateActions(new HashSet<>(){{
                add(Action.HARVEST_ALL);
                }}, false);
            } else {
            this.getActions().updateActions(new HashSet<>(){{
                add(Action.HARVEST_ALL);
                }}, true);
        }
    }

    public ArrayList<PlantChunk> getElements(){
        /*
         * Prints the elements present in the land
         */
        return this.chunks;        
    }
}