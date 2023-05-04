package model.place.land;

import java.util.ArrayList;

/**
 * Interface that represents a land
 */
public interface LandInteface {
    /**
     * Returns the number of elements present in the land
     * @return  Number of elements
     */
    public int getNumElements();

    /**
     * Returns the price of the land to sell
     * @return Price
     */
    public int getPrice();

    /**
     * Returns the elements present in the land
     * @return Elements
     */
    public ArrayList<? extends Object> getElements();

    /**
     * Updates the entities inside the land
     *  
     */
    public void update();
}
