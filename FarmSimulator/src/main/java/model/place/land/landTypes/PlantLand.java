package model.place.land.landTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.actors.actions.ActionsManager.Action;
import model.actors.actions.placeActions.PlaceActions;
import model.item.plants.Plant.PlantLife;
import model.place.Places;
import model.place.land.LandAbstract;
import model.place.land.chunks.PlantChunk;

/**
 * Land that contains chunks of land with plants
 */
public class PlantLand extends LandAbstract implements Iterable<PlantChunk>{
    /**
     *  Attributes
     */
    ArrayList<PlantChunk> chunks;
    
    /**
     * Constructor 
     * Initializes 10 chunks of land
     */
    public PlantLand(){
        super();
        super.type = Places.PLANT_LAND;
        this.chunks = new ArrayList<>(
            IntStream.range(0, 10)
                    .mapToObj(i -> new PlantChunk(null,this))
                    .collect(Collectors.toList())
        );
        this.actions = new PlaceActions(this);
    }

    /** 
     * Returns the number of land present
     * in the land
     * @return int
     */
    @Override
    public int getNumElements() {
        int elements = 0;
        for (PlantChunk p: this.chunks){
            if (p.getPlant() != null){
                elements += 1;
            }
        }
        return elements;
    }

    /**
     * Updates all the chunks in the land
     * 
     */
    public void update(){
        int count = 0;
        for (PlantChunk chunk: this.chunks){
            chunk.update();

            // check if there are harvestable plants
            if (chunk.getPlant() != null && chunk.getPlant().getLifeStage()==PlantLife.HARVESTABLE){
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

    /**
     * Prints the elements present in the land
     * @return ArrayList<PlantChunk> The elements present in the land
     */
    public ArrayList<PlantChunk> getElements(){
        return this.chunks;        
    }

    /**
     * Returns the iterator of the land
     * @return Iterator<PlantChunk>
     */
    @Override
    public Iterator<PlantChunk> iterator() {
        return chunks.iterator();
    }
}
