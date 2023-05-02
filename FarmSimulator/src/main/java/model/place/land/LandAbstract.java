package model.place.land;

import java.util.ArrayList;

import model.Constants;
import model.calendar.Calendar;
import model.place.Place;
import model.place.land.chunks.Chunk;

/**
 * Abstract class that represents a land
 */
public abstract class LandAbstract extends Place implements LandInteface {
    /**
     * Attributes
     */
    protected Calendar calendar = Calendar.getInstance();

    /**
     * Constructor
     */
    protected LandAbstract(){
        super();
    }

    /** 
     * Returns the price of the land to sell
     * @return int
     */
    public int getPrice() {
        return Constants.LAND_SELL_PRICE;
    }

    /**
     * Updates the entities inside the land
     * 
     * @throws UnsupportedOperationException The method is not implemented
     */
    public void update(){
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the elements present in the land
     * @return ArrayList<? extends Chunk>
     * @throws UnsupportedOperationException The method is not implemented
     */
    public ArrayList<? extends Chunk> getElements(){
        
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the number of elements present in the land
     * @return int
     * @throws UnsupportedOperationException The method is not implemented
     */
    public int getNumElements(){
        throw new UnsupportedOperationException();
    }
}
