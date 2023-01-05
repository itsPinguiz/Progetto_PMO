package Land;

import java.util.ArrayList;

import Actors.Actions.PlaceActions;

public class ChunkPlantLand extends LandAbstract {
    ArrayList<Chunk> chunks;
    PlaceActions actionChunkPlantLand;

    ChunkPlantLand(){
        this.type = "land of chunks";
        this.chunks = new ArrayList<>();
        this.actionChunkPlantLand = new PlaceActions(this);
        this.price = BASE_PRICE_LAND; //todo
    }

    @Override
    public String getLandType() {
        return this.type;
    }

    @Override
    public int getNumElements() {
        return chunks.size();
    }

    @Override
    public float getPrice() {
        return this.price;
    }

    private void setPrice(){
        //to do 
    }
}
