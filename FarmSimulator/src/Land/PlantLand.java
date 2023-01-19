package Land;

import java.util.ArrayList;

import Actors.Actions.PlaceActions;
import Land.enums.LandType;

public class PlantLand extends LandAbstract{
    /*Land that contains chunks of land with plants*/

    // attributes
    ArrayList<PlantChunk> chunks;
    PlaceActions actionChunkPlantLand;
    
    // constructor
    public PlantLand(){
        this.type = LandType.PLANT;
        this.chunks = new ArrayList<>();
        this.actionChunkPlantLand = new PlaceActions(this);
    }

    // methods
    @Override
    public LandType getLandType() {
        /*
         * Returns the type of the
         * land  
         */
        return this.type;
    }

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

    @Override
    public float getPrice() {
        /*
         *  Returns the price of the land to sell
         */
        return LandAbstract.SELL_PRICE;
    }
}
