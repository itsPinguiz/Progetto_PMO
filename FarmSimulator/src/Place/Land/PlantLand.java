package Place.Land;

import java.util.ArrayList;

import Actors.Actions.PlaceActions;
import Place.Places;

public class PlantLand extends LandAbstract{
    /*Land that contains chunks of land with plants*/

    // attributes
    ArrayList<PlantChunk> chunks;
    
    // constructor
    public PlantLand(){
        this.type = Places.PLANT_LAND;
        this.chunks = new ArrayList<>();
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
                elements =+ 1;
            }
        }
        return elements;
    }

    public ArrayList<PlantChunk> getChunks(){
        return this.chunks;
    }

    public void update(){
        this.chunks.forEach(chunk -> {chunk.update();});
    }
}
