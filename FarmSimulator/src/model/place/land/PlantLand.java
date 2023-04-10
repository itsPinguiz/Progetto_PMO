package model.place.land;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.actors.actions.PlaceActions;
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
        this.chunks.forEach(chunk -> {((PlantChunk)chunk).update();});
    }

    public ArrayList<PlantChunk> getElements(){
        /*
         * Prints the elements present in the land
         */
        return this.chunks;        
    }
}
